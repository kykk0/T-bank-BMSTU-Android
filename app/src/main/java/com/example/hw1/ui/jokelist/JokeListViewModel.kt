package com.example.hw1.ui.jokelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw1.data.model.Joke
import com.example.hw1.data.repository.JokeRepository
import kotlinx.coroutines.launch

class JokeListViewModel : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var isLoadingMoreJokes = false

    init {
        loadLocalJokes()
        loadNetworkJokes()
    }

    private fun loadLocalJokes() {
        viewModelScope.launch {
            _loading.value = true
            _jokes.value = JokeRepository.getLocalJokes()
            _loading.value = false
        }
    }

    fun loadNetworkJokes() {
        if (isLoadingMoreJokes) return

        viewModelScope.launch {
            isLoadingMoreJokes = true
            _loading.value = true

            val currentJokes = _jokes.value.orEmpty()
            val newJokes = JokeRepository.getNetworkJokes()
            _jokes.value = currentJokes + newJokes

            _loading.value = false
            isLoadingMoreJokes = false
        }
    }
}
