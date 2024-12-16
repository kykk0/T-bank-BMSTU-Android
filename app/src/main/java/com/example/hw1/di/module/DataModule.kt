package com.example.hw1.di.module

import android.content.Context
import androidx.room.Room
import com.example.hw1.data.datasource.local.JokeDao
import com.example.hw1.data.datasource.local.JokeDatabase
import com.example.hw1.data.datasource.remote.JokeApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE.toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): JokeApi {
        return retrofit.create(JokeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): JokeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            JokeDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideJokeDao(database: JokeDatabase): JokeDao {
        return database.jokeDao()
    }

    private companion object {
        private const val BASE_URL = "https://v2.jokeapi.dev/"
        private const val CONTENT_TYPE = "application/json"
        private const val DB_NAME = "joke_database"
    }
}