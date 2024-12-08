package com.example.hw1.data.entity

import com.example.hw1.R

enum class JokeSource {
    LOCAL,
    NETWORK,
    CACHED;
}

val JokeSource.displayName
    get() =
        when (this) {
            JokeSource.LOCAL -> R.string.source_local
            JokeSource.NETWORK -> R.string.source_network
            JokeSource.CACHED -> R.string.source_cache
        }
