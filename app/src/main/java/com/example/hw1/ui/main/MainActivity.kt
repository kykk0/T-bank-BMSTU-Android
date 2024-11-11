package com.example.hw1.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw1.databinding.ActivityMainBinding
import com.example.hw1.ui.joke_details.JokeDetailsActivity
import com.example.hw1.ui.main.adapters.JokeAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: JokeListViewModel by viewModels()

    private val jokeAdapter = JokeAdapter {
        startActivity(JokeDetailsActivity.getInstance(this, it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = jokeAdapter

        viewModel.jokes.observe(this) { jokeAdapter.setNewJokes(it) }
        viewModel.error.observe(this) { showError(it) }
        viewModel.loadJokes()
    }

    private fun showError(it: String?) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }
}
