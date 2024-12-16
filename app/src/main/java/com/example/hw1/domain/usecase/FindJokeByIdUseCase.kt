package com.example.hw1.domain.usecase

import com.example.hw1.domain.entity.Joke
import com.example.hw1.domain.repository.JokeRepository
import javax.inject.Inject

class FindJokeByIdUseCase @Inject constructor(
    private val jokeRepository: JokeRepository
) {
    suspend operator fun invoke(jokeId: Int): Joke? {
        return jokeRepository.findJokeById(jokeId)
    }
}