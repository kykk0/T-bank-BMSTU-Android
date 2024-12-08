package com.example.hw1.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "local_jokes")
data class LocalJoke(
    @SerialName("id")
    @PrimaryKey(autoGenerate = true) val id: Int,

    @SerialName("category")
    val category: String,

    @SerialName("question")
    val question: String,

    @SerialName("answer")
    val answer: String,

    @SerialName("source")
    val source: String,
)
