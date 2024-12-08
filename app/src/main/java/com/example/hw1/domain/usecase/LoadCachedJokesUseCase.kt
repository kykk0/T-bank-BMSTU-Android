package com.example.hw1.domain.usecase

import com.example.hw1.domain.entity.Joke
import com.example.hw1.domain.repository.JokeRepository

class LoadCachedJokesUseCase(private val jokeRepository: JokeRepository) {
    suspend operator fun invoke(): List<Joke> {
        return jokeRepository.loadCachedJokes()
    }
}