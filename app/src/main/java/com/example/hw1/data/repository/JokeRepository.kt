package com.example.hw1.data.repository

import com.example.hw1.data.model.Joke
import com.example.hw1.data.network.RetrofitInstance

object JokeRepository {
    private val localJokes = mutableListOf(
        Joke(-1, "Пиратство", "Что ищут шепелявые пираты?", "Фундук", "Локальный"),
        Joke(-2, "Дуэль", "Как называется дуэль двух каннибалов?", "Поединок", "Локальный"),
        Joke(-3, "Язык", "Какой уровень владения английского у террористов?", "С4", "Локальный"),
        Joke(-4, "Еда", "Почему рисовые шарики такие тяжёлые?", "Онигири", "Локальный"),
        Joke(-5, "Черепашка", "Как зовут черепашку, которая выросла?", "Черепавел", "Локальный"),
        Joke(
            -6,
            "Свидание",
            "Почему программист отказался идти на свидание?",
            "Он сказал, что у него все серьезные отношения начинаются с фазы " +
                    "тестирования, потом идут баги, потом — деплой, " +
                    "а в итоге всё равно приходится делать рефакторинг.",
            "Локальный"
        ),
        Joke(
            -7,
            "Автоваз",
            "На директора автоваза решили завести уголовное дело",
            "Но оно не завелось",
            "Локальный"
        ),
    )

    private val apiJokes = mutableListOf<Joke>()

    private val allJokes: List<Joke>
        get() = localJokes + apiJokes

    suspend fun getNetworkJokes(): List<Joke> {
        val networkJokes = RetrofitInstance.api.getJokes().jokes.map {
            Joke(
                id = it.id,
                category = it.category,
                question = it.question,
                answer = it.answer,
                source = "Из сети"
            )
        }
        networkJokes.forEach { joke ->
            if (apiJokes.none { it.id == joke.id }) {
                apiJokes.add(joke)
            }
        }
        return networkJokes
    }

    fun getLocalJokes(): List<Joke> = localJokes

    fun findJokeById(jokeId: Int): Joke? = allJokes.find { it.id == jokeId }

    fun addJoke(category: String, question: String, answer: String) {
        val newJoke = Joke(
            id = getMinJokeId() - 1,
            category = category,
            question = question,
            answer = answer,
            source = "Локальный",
        )
        localJokes.add(newJoke)
    }

    private fun getMinJokeId(): Int = localJokes.minOfOrNull { it.id } ?: 0
}
