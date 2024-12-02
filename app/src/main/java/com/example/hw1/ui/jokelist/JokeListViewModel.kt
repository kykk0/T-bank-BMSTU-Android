package com.example.hw1.ui.jokelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw1.data.db.JokeDatabase
import com.example.hw1.data.model.Joke
import com.example.hw1.data.repository.JokeRepository
import kotlinx.coroutines.launch

class JokeListViewModel : ViewModel() {

    private val _jokes = MutableLiveData<List<Joke>>()
    val jokes: LiveData<List<Joke>> = _jokes

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val repository: JokeRepository by lazy {
        JokeRepository(JokeDatabase.INSTANCE.jokeDao())
    }

    init {
        loadInitialJokes()
    }

    private fun loadInitialJokes() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val localJokes = repository.initialize()
                _jokes.value = localJokes

                val newJokes = repository.loadMoreJokes()
                _jokes.value = localJokes + newJokes
            } catch (e: Exception) {
                val cachedJokes = repository.loadCachedJokes()
                if(cachedJokes.isEmpty()){
                    _errorMessage.value = "Проблемы с интернетом, попробуйте позже"
                } else {
                    _jokes.value = cachedJokes
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadMoreJokes() {
        if (!canLoadMore()) return
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val currentJokes = _jokes.value.orEmpty()
                val newJokes = repository.loadMoreJokes()
                _jokes.value = currentJokes + newJokes
            } catch (e: Exception) {
                val localJokes = repository.getLocalJokes()
                val cachedJokes = repository.loadCachedJokes()
                _jokes.value = localJokes + cachedJokes
                _errorMessage.value = "Ошибка при загрузке шуток из сети. Вот старые шутки:)"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun canLoadMore(): Boolean {
        return isLoading.value == false
    }
}
