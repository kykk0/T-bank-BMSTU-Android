package com.example.hw1.domain.entity

import com.example.hw1.data.entity.JokeSource

data class Joke(
    val id: Int,
    val category: String,
    val question: String,
    val answer: String,
    val source: JokeSource,
)
