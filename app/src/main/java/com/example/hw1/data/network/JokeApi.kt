package com.example.hw1.data.network

import com.example.hw1.data.model.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApi {
    @GET("joke/Any")
    suspend fun getJokes(
        @Query("blacklistFlags") blacklistFlags: String = "nsfw,religious,political,racist,sexist,explicit",
        @Query("type") type: String = "twopart",
        @Query("amount") amount: Int = 10
    ): JokeResponse
}