package com.example.hw1.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw1.data.model.Joke
import com.example.hw1.data.repository.JokeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JokeListViewModel : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private var isDataLoaded = false

    fun loadJokes() {
        if (isDataLoaded) return

        viewModelScope.launch {
            _loading.value = true
            delay(3000)
            _jokes.value = JokeRepository.getJokeList()
            _loading.value = false
            isDataLoaded = true
        }
    }
}
