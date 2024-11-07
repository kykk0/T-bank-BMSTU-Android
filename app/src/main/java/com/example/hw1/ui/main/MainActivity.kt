package com.example.hw1.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw1.databinding.ActivityMainBinding
import com.example.hw1.ui.joke_details.JokeDetailsActivity
import com.example.hw1.ui.main.adapters.JokeAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: JokeViewModel

    private val jokeAdapter = JokeAdapter {
        startActivity(JokeDetailsActivity.getInstance(this, it))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = jokeAdapter

        initViewModel()
        viewModel.loadJokes()
    }

    private fun initViewModel() {
        val factory = JokesViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[JokeViewModel::class.java]

        viewModel.jokes.observe(this) { jokeAdapter.setNewJokes(it) }
        viewModel.error.observe(this) { showError(it) }
    }

    private fun showError(it: String?) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
    }
}
