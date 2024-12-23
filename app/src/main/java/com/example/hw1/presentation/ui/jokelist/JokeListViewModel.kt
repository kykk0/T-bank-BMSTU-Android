package com.example.hw1.presentation.ui.jokelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw1.domain.entity.Joke
import com.example.hw1.domain.usecase.GetLocalJokesUseCase
import com.example.hw1.domain.usecase.InitializeUseCase
import com.example.hw1.domain.usecase.LoadCachedJokesUseCase
import com.example.hw1.domain.usecase.LoadMoreJokesUseCase
import kotlinx.coroutines.launch

class JokeListViewModel(
    private val getLocalJokesUseCase: GetLocalJokesUseCase,
    private val loadMoreJokesUseCase: LoadMoreJokesUseCase,
    private val loadCachedJokesUseCase: LoadCachedJokesUseCase,
    private val initializeUseCase : InitializeUseCase,
) : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        loadInitialJokes()
    }

    private fun loadInitialJokes() {
        viewModelScope.launch {
            _isLoading.value = true
            val localJokes = initializeUseCase.invoke()
            _jokes.value = localJokes
            try {
                val newJokes = loadMoreJokesUseCase.invoke()
                _jokes.value = localJokes + newJokes
            } catch (e: Exception) {
                val cachedJokes = loadCachedJokesUseCase.invoke()
                if (cachedJokes.isEmpty()) {
                    _errorMessage.value = "ERROR_INTERNET"
                } else {
                    _jokes.value = localJokes + cachedJokes
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMoreJokes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val currentJokes = _jokes.value.orEmpty()
                val newJokes = loadMoreJokesUseCase.invoke()
                _jokes.value = currentJokes + newJokes
            } catch (e: Exception) {
                val localJokes = getLocalJokesUseCase.invoke()
                val cachedJokes = loadCachedJokesUseCase.invoke()
                _jokes.value = localJokes + cachedJokes
                _errorMessage.value = "ERROR_LOADING_JOKES"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun canLoadMore(): Boolean {
        return isLoading.value == false
    }
}
