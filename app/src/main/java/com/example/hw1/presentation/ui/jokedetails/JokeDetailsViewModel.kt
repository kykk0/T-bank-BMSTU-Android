package com.example.hw1.presentation.ui.jokedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw1.domain.entity.Joke
import com.example.hw1.domain.usecase.FindJokeByIdUseCase
import kotlinx.coroutines.launch

class JokeDetailsViewModel(
    private val findJokeByIdUseCase: FindJokeByIdUseCase,
) : ViewModel() {
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _selectedJoke = MutableLiveData<Joke?>()
    val selectedJoke: LiveData<Joke?> = _selectedJoke

    fun selectJokeById(jokeId: Int) {
        viewModelScope.launch {
            val joke = findJokeByIdUseCase.invoke(jokeId)
            if (joke != null) {
                _selectedJoke.value = joke
            } else {
                _error.value = "Шутка не найдена"
            }
        }
    }
}