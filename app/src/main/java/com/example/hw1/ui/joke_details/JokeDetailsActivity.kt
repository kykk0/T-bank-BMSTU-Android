package com.example.hw1.ui.joke_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hw1.R
import com.example.hw1.data.model.Joke
import com.example.hw1.databinding.ActivityJokeDetailsBinding

class JokeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJokeDetailsBinding
    private val viewModel: JokeDetailsViewModel by viewModels()

    private var jokeId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        jokeId = intent.getIntExtra(JOKE_ID_EXTRA, -1)
        if (jokeId == -1) {
            showError("Invalid id")
        } else {
            viewModel.selectJokeById(jokeId)
            viewModel.selectedJoke.observe(this) { joke ->
                if (joke != null) {
                    setupJokeData(joke)
                } else {
                    showError("Joke not found")
                }
            }
        }
    }

    companion object {
        private const val JOKE_ID_EXTRA = "JOKE_ID"

        fun getInstance(context: Context, jokeId: Int): Intent {
            return Intent(context, JokeDetailsActivity::class.java).apply {
                putExtra(JOKE_ID_EXTRA, jokeId)
            }
        }
    }

    private fun setupJokeData(joke: Joke) {
        with(binding) {
            tvCategory.text = getString(R.string.joke_category, joke.category)
            tvQuestion.text = getString(R.string.joke_question, joke.question)
            tvAnswer.text = getString(R.string.joke_answer, joke.answer)
        }
    }

    private fun showError(it: String?) {
        Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        finish()
    }
}
