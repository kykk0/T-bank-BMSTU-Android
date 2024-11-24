package com.example.hw1.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class JokeResponse(
    @SerialName("jokes")
    val jokes: List<NetworkJoke>
)

@Serializable
data class NetworkJoke(
    @SerialName("category")
    val category: String,
    @SerialName("setup")
    val question: String,
    @SerialName("delivery")
    val answer: String,
    @SerialName("id")
    val id: Int
)
