package com.example.hw1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw1.data.model.Joke
import com.example.hw1.data.repository.JokeRepository

class JokeDetailsViewModel : ViewModel() {
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _selectedJoke = MutableLiveData<Joke?>()
    val selectedJoke: LiveData<Joke?> = _selectedJoke

    fun selectJokeById(jokeId: Int) {
        val joke = JokeRepository.findJokeById(jokeId)
        if (joke != null) {
            _selectedJoke.value = joke
        } else {
            _error.value = "Joke not found"
        }
    }
}