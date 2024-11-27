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

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        loadJokes()
    }

    private fun loadJokes() {
        viewModelScope.launch {
            _isLoading.value = true
            _jokes.value = JokeRepository.getJokes(loadMore = false)
            _isLoading.value = false
        }
    }

    fun loadMoreJokes() {
        if (!canLoadMore()) return
        viewModelScope.launch {
            _isLoading.value = true
            _jokes.value = JokeRepository.getJokes(loadMore = true)
            _isLoading.value = false
        }
    }

    fun canLoadMore(): Boolean {
        return isLoading.value == false
    }
}
