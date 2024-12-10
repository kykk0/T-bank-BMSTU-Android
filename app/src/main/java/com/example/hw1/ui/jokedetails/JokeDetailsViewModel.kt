package com.example.hw1.ui.jokedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw1.data.db.JokeDatabase
import com.example.hw1.data.model.Joke
import com.example.hw1.data.repository.JokeRepository
import kotlinx.coroutines.launch

class JokeDetailsViewModel : ViewModel() {
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _selectedJoke = MutableLiveData<Joke?>()
    val selectedJoke: LiveData<Joke?> = _selectedJoke

    private val repository: JokeRepository by lazy {
        JokeRepository(JokeDatabase.INSTANCE.jokeDao())
    }

    fun selectJokeById(jokeId: Int) {
        viewModelScope.launch {
            val joke = repository.findJokeById(jokeId)
            if (joke != null) {
                _selectedJoke.value = joke
            } else {
                _error.value = "Шутка не найдена"
            }
        }
    }
}