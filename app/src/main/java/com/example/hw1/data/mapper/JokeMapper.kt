package com.example.hw1.data.mapper

import com.example.hw1.data.entity.LocalJoke
import com.example.hw1.data.entity.CachedJoke
import com.example.hw1.domain.entity.Joke
import com.example.hw1.data.entity.JokeSource

object JokeMapper {

    fun mapToJokeFromLoc(localJoke: LocalJoke): Joke {
        return Joke(
            id = localJoke.id,
            category = localJoke.category,
            question = localJoke.question,
            answer = localJoke.answer,
            source = JokeSource.LOCAL
        )
    }

    fun mapToJokeFromNet(cachedJoke: CachedJoke): Joke {
        return Joke(
            id = cachedJoke.id,
            category = cachedJoke.category,
            question = cachedJoke.question,
            answer = cachedJoke.answer,
            source = JokeSource.NETWORK
        )
    }

    fun mapToJokeFromCache(cachedJoke: CachedJoke): Joke {
        return Joke(
            id = cachedJoke.id,
            category = cachedJoke.category,
            question = cachedJoke.question,
            answer = cachedJoke.answer,
            source = JokeSource.CACHED
        )
    }
}
