package com.example.hw1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw1.data.model.Joke
import com.example.hw1.data.repository.JokeRepository

class JokeViewModel : ViewModel() {
    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _selectedJoke = MutableLiveData<Joke>()
    val selectedJoke: LiveData<Joke> = _selectedJoke

    fun loadJokes() {
        try {
            _jokes.value = JokeRepository.getJokeList()
        } catch (e: Exception) {
            _error.value = "Failed to load jokes"
        }
    }

    fun selectJokeById(jokeId: Int) {
        val joke = JokeRepository.jokes.find { it.id == jokeId }
        if (joke != null) {
            _selectedJoke.value = joke
        } else {
            _error.value = "Joke not found"
        }
    }
}
