package com.example.hw1.data.mapper

import com.example.hw1.data.entity.LocalJoke
import com.example.hw1.data.entity.CachedJoke
import com.example.hw1.domain.entity.Joke
import com.example.hw1.data.entity.JokeSource
import javax.inject.Inject

class JokeMapper @Inject constructor() {

    fun mapToJokeFromLoc(localJoke: LocalJoke): Joke {
        return with(localJoke) {
            Joke(
                id = id,
                category = category,
                question = question,
                answer = answer,
                source = JokeSource.LOCAL
            )
        }
    }

    fun mapToJokeFromNet(cachedJoke: CachedJoke): Joke {
        return with(cachedJoke) {
            Joke(
                id = id,
                category = category,
                question = question,
                answer = answer,
                source = JokeSource.NETWORK
            )
        }
    }

    fun mapToJokeFromCache(cachedJoke: CachedJoke): Joke {
        return with(cachedJoke) {
            Joke(
                id = id,
                category = category,
                question = question,
                answer = answer,
                source = JokeSource.CACHED
            )
        }
    }
}
