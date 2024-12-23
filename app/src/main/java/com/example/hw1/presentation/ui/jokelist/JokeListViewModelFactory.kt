package com.example.hw1.presentation.ui.jokelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hw1.domain.usecase.GetLocalJokesUseCase
import com.example.hw1.domain.usecase.LoadCachedJokesUseCase
import com.example.hw1.domain.usecase.LoadMoreJokesUseCase
import com.example.hw1.domain.usecase.InitializeUseCase

class JokeListViewModelFactory(
    private val getLocalJokesUseCase: GetLocalJokesUseCase,
    private val loadMoreJokesUseCase: LoadMoreJokesUseCase,
    private val loadCachedJokesUseCase: LoadCachedJokesUseCase,
    private val initializeUseCase: InitializeUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JokeListViewModel::class.java)) {
            return JokeListViewModel(
                getLocalJokesUseCase,
                loadMoreJokesUseCase,
                loadCachedJokesUseCase,
                initializeUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
