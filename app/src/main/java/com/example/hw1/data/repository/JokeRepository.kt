package com.example.hw1.data.repository

import com.example.hw1.data.model.Joke

object JokeRepository {
    private val jokes = mutableListOf<Joke>(
        Joke(1, "Пиратство", "Что ищут шепелявые пираты?", "Фундук"),
        Joke(2, "Дуэль", "Как называется дуэль двух каннибалов?", "Поединок"),
        Joke(3, "Язык", "Какой уровень владения английского у террористов?", "С4"),
        Joke(4, "Еда", "Почему рисовые шарики такие тяжёлые?", "Онигири"),
        Joke(5, "Черепашка", "Как зовут черепашку, которая выросла?", "Черепавел"),
        Joke(
            6,
            "Свидание",
            "Почему программист отказался идти на свидание?",
            "Он сказал, что у него все серьезные отношения начинаются с фазы " +
                    "тестирования, потом идут баги, потом — деплой, " +
                    "а в итоге всё равно приходится делать рефакторинг."
        ),
        Joke(
            7,
            "Автоваз",
            "На директора автоваза решили завести уголовное дело",
            "Но оно не завелось"
        ),
    )

    fun getJokeList(): List<Joke> {
        return jokes.toList()
    }

    fun findJokeById(jokeId: Int): Joke? {
        return jokes.find { it.id == jokeId }
    }

    fun addJoke(joke: Joke) {
        jokes.add(joke)
    }

    fun getMaxJokeId(): Int {
        return jokes.maxOfOrNull { it.id } ?: 0
    }

}
