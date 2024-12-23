package com.example.hw1.domain.repository

import com.example.hw1.domain.entity.Joke

interface JokeRepository {
    suspend fun getLocalJokes(): List<Joke>

    suspend fun loadCachedJokes(): List<Joke>

    suspend fun loadMoreJokes(): List<Joke>

    suspend fun addLocalJoke(category: String, question: String, answer: String)

    suspend fun findJokeById(jokeId: Int): Joke?

    suspend fun initialize(): List<Joke>
}