package com.example.hw1.domain.usecase

import com.example.hw1.domain.repository.JokeRepository

class AddLocalJokeUseCase(private val jokeRepository: JokeRepository) {
    suspend operator fun invoke(category: String, question: String, answer: String) {
        jokeRepository.addLocalJoke(category, question, answer)
    }
}