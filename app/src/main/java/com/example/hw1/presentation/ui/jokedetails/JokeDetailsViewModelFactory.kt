package com.example.hw1.presentation.ui.jokedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw1.domain.usecase.FindJokeByIdUseCase
import javax.inject.Inject

class JokeDetailsViewModelFactory @Inject constructor(
    private val findJokeByIdUseCase: FindJokeByIdUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokeDetailsViewModel::class.java)) {
            return JokeDetailsViewModel(findJokeByIdUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}