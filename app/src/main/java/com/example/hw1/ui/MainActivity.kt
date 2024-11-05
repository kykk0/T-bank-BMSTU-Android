package com.example.hw1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw1.data.JokeGenerator
import com.example.hw1.databinding.ActivityMainBinding
import com.example.hw1.ui.adapters.JokeAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val jokeGenerator = JokeGenerator
    private val jokeAdapter = JokeAdapter{
        startActivity(JokeDetailsActivity.getInstance(this, it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jokes = jokeGenerator.getJokeList()
        jokeAdapter.setNewJokes(jokes)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = jokeAdapter
    }
}
