package com.example.hw1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw1.data.Joke
import com.example.hw1.databinding.ActivityMainBinding
import com.example.hw1.ui.adapters.JokeAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var jokeAdapter: JokeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jokes = listOf(
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
            )
        )

        jokeAdapter = JokeAdapter(jokes)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = jokeAdapter
    }
}
