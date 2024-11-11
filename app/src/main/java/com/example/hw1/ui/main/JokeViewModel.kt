package com.example.hw1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hw1.data.model.Joke
import com.example.hw1.data.repository.JokeRepository

class JokeViewModel : ViewModel() {
    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes

    private val _selectedJoke = MutableLiveData<Joke>()
    val selectedJoke: LiveData<Joke> = _selectedJoke

    fun loadJokes() {
        _jokes.value = JokeRepository.getJokeList()
    }

    fun selectJokeById(jokeId: Int) {
        _selectedJoke.value = JokeRepository.jokes.find { it.id == jokeId }
    }
}
