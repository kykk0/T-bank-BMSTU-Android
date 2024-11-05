package com.example.hw1.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hw1.R
import com.example.hw1.data.Joke
import com.example.hw1.data.JokeGenerator
import com.example.hw1.databinding.ActivityJokeDetailsBinding

class JokeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeDetailsBinding

    private val jokeGenerator = JokeGenerator

    private var jokePosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleExtra()
    }

    companion object {
        private const val JOKE_POSITION_EXTRA = "JOKE_POSITION"

        fun getInstance(context: Context, jokePosition: Int): Intent {
            return Intent(context, JokeDetailsActivity::class.java).apply {
                putExtra(JOKE_POSITION_EXTRA, jokePosition)
            }
        }
    }

    private fun handleExtra() {
        jokePosition = intent.getIntExtra(JOKE_POSITION_EXTRA, -1)

        if (jokePosition == -1) {
            handleError()
        } else {
            val item = jokeGenerator.jokes[jokePosition] as? Joke
            if (item != null) {
                setupJokeData(item)
            } else {
                handleError()
            }
        }
    }

    private fun setupJokeData(joke: Joke) {
        with(binding){
            binding.tvCategory.text = getString(R.string.joke_category, joke.category)
            binding.tvQuestion.text = getString(R.string.joke_question, joke.question)
            binding.tvAnswer.text = getString(R.string.joke_answer, joke.answer)
        }

    }

    private fun handleError() {
        Toast.makeText(this, "Invalid Joke data", Toast.LENGTH_SHORT).show()
        finish()
    }
}
