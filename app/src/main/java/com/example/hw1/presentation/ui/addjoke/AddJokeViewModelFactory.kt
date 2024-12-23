package com.example.hw1.presentation.ui.addjoke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw1.domain.usecase.AddLocalJokeUseCase

class AddJokeViewModelFactory(
    private val addLocalJokeUseCase: AddLocalJokeUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddJokeViewModel::class.java)) {
            return AddJokeViewModel(addLocalJokeUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
