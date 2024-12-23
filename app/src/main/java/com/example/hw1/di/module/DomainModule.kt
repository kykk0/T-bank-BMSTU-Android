package com.example.hw1.di.module

import com.example.hw1.domain.repository.JokeRepository
import com.example.hw1.domain.usecase.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    fun provideAddLocalJokeUseCase(jokeRepository: JokeRepository): AddLocalJokeUseCase {
        return AddLocalJokeUseCase(jokeRepository)
    }

    @Provides
    @Singleton
    fun provideFindJokeByIdUseCase(jokeRepository: JokeRepository): FindJokeByIdUseCase {
        return FindJokeByIdUseCase(jokeRepository)
    }

    @Provides
    @Singleton
    fun provideGetLocalJokesUseCase(jokeRepository: JokeRepository): GetLocalJokesUseCase {
        return GetLocalJokesUseCase(jokeRepository)
    }

    @Provides
    @Singleton
    fun provideInitializeUseCase(jokeRepository: JokeRepository): InitializeUseCase {
        return InitializeUseCase(jokeRepository)
    }

    @Provides
    @Singleton
    fun provideLoadCachedJokesUseCase(jokeRepository: JokeRepository): LoadCachedJokesUseCase {
        return LoadCachedJokesUseCase(jokeRepository)
    }

    @Provides
    @Singleton
    fun provideLoadMoreJokesUseCase(jokeRepository: JokeRepository): LoadMoreJokesUseCase {
        return LoadMoreJokesUseCase(jokeRepository)
    }
}