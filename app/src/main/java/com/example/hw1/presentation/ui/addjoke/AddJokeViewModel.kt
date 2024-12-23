package com.example.hw1.presentation.ui.addjoke

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw1.domain.usecase.AddLocalJokeUseCase
import kotlinx.coroutines.launch

class AddJokeViewModel(
    private val addLocalJokeUseCase: AddLocalJokeUseCase,
) : ViewModel() {

    private val _addJokeResult = MutableLiveData<Boolean>()
    val addJokeResult: LiveData<Boolean> = _addJokeResult

    fun addJoke(category: String, question: String, answer: String) {
        viewModelScope.launch {
            try {
                addLocalJokeUseCase.invoke(category, question, answer)
                _addJokeResult.value = true
            } catch (e: Exception) {
                _addJokeResult.value = false
            }
        }
    }
}
