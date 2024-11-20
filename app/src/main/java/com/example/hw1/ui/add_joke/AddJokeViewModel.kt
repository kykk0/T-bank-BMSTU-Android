package com.example.hw1.ui.add_joke

import androidx.lifecycle.ViewModel
import com.example.hw1.data.repository.JokeRepository

class AddJokeViewModel : ViewModel() {
    fun addJoke(category: String, question: String, answer: String) {
        JokeRepository.addJoke(category, question, answer)
    }
}

