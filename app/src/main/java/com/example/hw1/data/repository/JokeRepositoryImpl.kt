package com.example.hw1.data.repository

import com.example.hw1.data.mapper.JokeMapper
import com.example.hw1.data.entity.CachedJoke
import com.example.hw1.data.datasource.local.JokeDao
import com.example.hw1.data.entity.LocalJoke
import com.example.hw1.domain.entity.Joke
import com.example.hw1.data.entity.JokeSource
import com.example.hw1.data.datasource.remote.RetrofitInstance
import com.example.hw1.domain.repository.JokeRepository

class JokeRepositoryImpl(
    private val jokeDao: JokeDao,
    private val jokeMapper: JokeMapper,
) :
    JokeRepository {
    private companion object {
        const val CACHE_EXPIRATION_TIME_MS = 24 * 60 * 60 * 1000L
    }

    override suspend fun getLocalJokes(): List<Joke> {
        return jokeDao.getAllLocalJokes().map { jokeMapper.mapToJokeFromLoc(it) }
    }

    override suspend fun loadMoreJokes(): List<Joke> {
        val jokesFromNetwork = fetchJokesFromNetwork()
        saveJokesToCache(jokesFromNetwork)
        return jokesFromNetwork.map { jokeMapper.mapToJokeFromNet(it) }
    }

    private suspend fun fetchJokesFromNetwork(): List<CachedJoke> {
        val networkJokes = RetrofitInstance.api.getJokes().jokes.map {
            CachedJoke(
                id = it.id,
                category = it.category,
                question = it.question,
                answer = it.answer,
                source = JokeSource.NETWORK.toString(),
                timestamp = System.currentTimeMillis()
            )
        }
        return networkJokes
    }

    private suspend fun saveJokesToCache(jokes: List<CachedJoke>) {
        jokes.forEach { joke ->
            jokeDao.insertCachedJoke(joke.copy(source = JokeSource.CACHED.toString()))
        }
    }

    override suspend fun loadCachedJokes(): List<Joke> {
        val jokes = mutableListOf<Joke>()

        clearExpiredCache()

        val cachedJokes = jokeDao.getCachedJokes()
        if (cachedJokes.isNotEmpty() && isCacheFresh(cachedJokes)) {
            jokes.addAll(cachedJokes.map { jokeMapper.mapToJokeFromCache(it) })
        }
        return jokes
    }

    private fun isCacheFresh(cachedJokes: List<CachedJoke>): Boolean {
        val cacheTime = cachedJokes.firstOrNull()?.timestamp ?: return false
        return System.currentTimeMillis() - cacheTime < CACHE_EXPIRATION_TIME_MS
    }

    override suspend fun addLocalJoke(category: String, question: String, answer: String) {
        val localJoke = LocalJoke(
            id = 0,
            category = category,
            question = question,
            answer = answer,
            source = JokeSource.LOCAL.toString()
        )
        jokeDao.insertLocalJoke(localJoke)
    }

    override suspend fun findJokeById(jokeId: Int): Joke? {
        val localJoke = jokeDao.getLocalJokeById(jokeId)
        val cachedJoke = jokeDao.getCachedJokeById(jokeId)

        return localJoke?.let { jokeMapper.mapToJokeFromLoc(it) }
            ?: cachedJoke?.let { jokeMapper.mapToJokeFromCache(it) }
    }

    override suspend fun initialize(): List<Joke> {
        val existingJokes = getLocalJokes()
        if (existingJokes.isEmpty()) {
            addLocalJoke(
                "Пиратство",
                "Что ищут шепелявые пираты?",
                "Фундук",
            )
            addLocalJoke(
                "Язык",
                "Какой уровень владения английского у террористов?",
                "С4",
            )
            addLocalJoke(
                "Еда",
                "Почему рисовые шарики такие тяжёлые?",
                "Онигири",
            )
            addLocalJoke(
                "Черепашка",
                "Как зовут черепашку, которая выросла?",
                "Черепавел",
            )
        }
        return jokeDao.getAllLocalJokes().map { jokeMapper.mapToJokeFromLoc(it) }
    }

    private suspend fun clearExpiredCache() {
        val expirationTime = System.currentTimeMillis() - CACHE_EXPIRATION_TIME_MS
        jokeDao.deleteOldCache(expirationTime)
    }
}
