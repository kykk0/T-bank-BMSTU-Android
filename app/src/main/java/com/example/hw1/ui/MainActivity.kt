package com.example.hw1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw1.data.Joke
import com.example.hw1.databinding.ActivityMainBinding
import com.example.hw1.ui.adapters.JokeAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jokes = listOf(
            Joke("Пиратство", "Что ищут шепелявые пираты?", "Фундук"),
            Joke("Дуэль", "Как называется дуэль двух каннибалов?", "Поединок"),
            Joke("Язык", "Какой уровень владения английского у террористов?", "С4"),
            Joke("Еда", "Почему рисовые шарики такие тяжёлые?", "Онигири"),
            Joke("Черепашка", "Как зовут черепашку, которая выросла?", "Черепавел"),
            Joke(
                "Свидание",
                "Почему программист отказался идти на свидание?",
                "Он сказал, что у него все серьезные отношения начинаются с фазы " +
                        "тестирования, потом идут баги, потом — деплой, " +
                        "а в итоге всё равно приходится делать рефакторинг."
            ),
            Joke(
                "Автоваз",
                "На директора автоваза решили завести уголовное дело",
                "Но оно не завелось"
            )
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = JokeAdapter(jokes)

    }
}
