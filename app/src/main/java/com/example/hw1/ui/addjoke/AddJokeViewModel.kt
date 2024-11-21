package com.example.hw1.ui.addjoke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw1.data.repository.JokeRepository
import kotlinx.coroutines.launch

class AddJokeViewModel : ViewModel() {

    private val _addJokeResult = MutableLiveData<Boolean>()
    val addJokeResult: LiveData<Boolean> = _addJokeResult

    fun addJoke(category: String, question: String, answer: String) {
        viewModelScope.launch {
            JokeRepository.addJoke(category, question, answer)
            _addJokeResult.value = true
        }
    }
}
