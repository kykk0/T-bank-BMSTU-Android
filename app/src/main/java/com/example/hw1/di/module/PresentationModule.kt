package com.example.hw1.di.module

import com.example.hw1.presentation.ui.addjoke.AddJokeViewModel
import com.example.hw1.presentation.ui.jokedetails.JokeDetailsViewModel
import com.example.hw1.presentation.ui.jokelist.JokeListViewModel
import com.example.hw1.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideAddJokeViewModel(
        addLocalJokeUseCase: AddLocalJokeUseCase
    ): AddJokeViewModel {
        return AddJokeViewModel(addLocalJokeUseCase)
    }

    @Provides
    fun provideJokeDetailsViewModel(
        findJokeByIdUseCase: FindJokeByIdUseCase
    ): JokeDetailsViewModel {
        return JokeDetailsViewModel(findJokeByIdUseCase)
    }

    @Provides
    fun provideJokeListViewModel(
        getLocalJokesUseCase: GetLocalJokesUseCase,
        loadMoreJokesUseCase: LoadMoreJokesUseCase,
        loadCachedJokesUseCase: LoadCachedJokesUseCase,
        initializeUseCase: InitializeUseCase
    ): JokeListViewModel {
        return JokeListViewModel(getLocalJokesUseCase, loadMoreJokesUseCase, loadCachedJokesUseCase, initializeUseCase)
    }
}
