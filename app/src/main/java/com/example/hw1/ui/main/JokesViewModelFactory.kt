package com.example.hw1.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class JokesViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(JokeViewModel::class.java) -> {
                JokeViewModel() as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
