package com.example.hw1.data.repository

import com.example.hw1.data.mapper.JokeMapper
import com.example.hw1.data.db.CachedJoke
import com.example.hw1.data.db.JokeDao
import com.example.hw1.data.db.LocalJoke
import com.example.hw1.data.model.Joke
import com.example.hw1.data.model.JokeSource
import com.example.hw1.data.network.RetrofitInstance

class JokeRepository(private val jokeDao: JokeDao) {
    private companion object {
        const val CACHE_EXPIRATION_TIME_MS = 24 * 60 * 60 * 1000L
    }

    suspend fun getLocalJokes(): List<Joke> {
        return jokeDao.getAllLocalJokes().map { JokeMapper.mapToJokeFromLoc(it) }
    }

    suspend fun loadMoreJokes(): List<Joke> {
        val jokesFromNetwork = fetchJokesFromNetwork()
        saveJokesToCache(jokesFromNetwork)
        return jokesFromNetwork.map { JokeMapper.mapToJokeFromNet(it) }
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

    suspend fun loadCachedJokes(): List<Joke> {
        val jokes = mutableListOf<Joke>()

        clearExpiredCache()

        val cachedJokes = jokeDao.getCachedJokes()
        if (cachedJokes.isNotEmpty() && isCacheFresh(cachedJokes)) {
            jokes.addAll(cachedJokes.map { JokeMapper.mapToJokeFromCache(it) })
        }
        return jokes
    }

    private fun isCacheFresh(cachedJokes: List<CachedJoke>): Boolean {
        val cacheTime = cachedJokes.firstOrNull()?.timestamp ?: return false
        return System.currentTimeMillis() - cacheTime < CACHE_EXPIRATION_TIME_MS
    }

    suspend fun addLocalJoke(category: String, question: String, answer: String) {
        val localJoke = LocalJoke(
            id = 0,
            category = category,
            question = question,
            answer = answer,
            source = JokeSource.LOCAL.toString()
        )
        jokeDao.insertLocalJoke(localJoke)
    }

    suspend fun findJokeById(jokeId: Int): Joke? {
        val localJoke = jokeDao.getLocalJokeById(jokeId)
        val cachedJoke = jokeDao.getCachedJokeById(jokeId)

        return localJoke?.let { JokeMapper.mapToJokeFromLoc(it) }
            ?: cachedJoke?.let { JokeMapper.mapToJokeFromCache(it) }
    }

    suspend fun initialize(): List<Joke> {
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
        return jokeDao.getAllLocalJokes().map { JokeMapper.mapToJokeFromLoc(it) }
    }

    private suspend fun clearExpiredCache() {
        val expirationTime = System.currentTimeMillis() - CACHE_EXPIRATION_TIME_MS
        jokeDao.deleteOldCache(expirationTime)
    }
}
