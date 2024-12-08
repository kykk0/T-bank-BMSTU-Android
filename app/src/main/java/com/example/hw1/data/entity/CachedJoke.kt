package com.example.hw1.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "cached_jokes")
data class CachedJoke(
    @SerialName("id")
    @PrimaryKey val id: Int,

    @SerialName("category")
    val category: String,

    @SerialName("question")
    val question: String,

    @SerialName("answer")
    val answer: String,

    @SerialName("source")
    val source: String,

    @SerialName("timestamp")
    val timestamp: Long = System.currentTimeMillis(),
)
