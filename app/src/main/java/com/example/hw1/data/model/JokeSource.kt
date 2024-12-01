package com.example.hw1.data.model

enum class JokeSource {
    LOCAL,
    NETWORK,
    CACHED;

    val localizedName: String
        get() = when (this) {
            LOCAL -> "Локальный"
            NETWORK -> "Из сети"
            CACHED -> "Из кэша"
        }
}
