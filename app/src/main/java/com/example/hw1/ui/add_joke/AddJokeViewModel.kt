package com.example.hw1.ui.add_joke

import androidx.lifecycle.ViewModel
import com.example.hw1.data.model.Joke
import com.example.hw1.data.repository.JokeRepository

class AddJokeViewModel : ViewModel() {
    fun addJoke(category: String, question: String, answer: String) {
        val newJoke = Joke(
            id = JokeRepository.getMaxJokeId() + 1,
            category = category,
            question = question,
            answer = answer
        )
        JokeRepository.addJoke(newJoke)
    }
}
