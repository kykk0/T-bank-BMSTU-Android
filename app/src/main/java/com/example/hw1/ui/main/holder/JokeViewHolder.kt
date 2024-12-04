package com.example.hw1.ui.main.holder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.data.model.Joke
import com.example.hw1.data.model.JokeSource
import com.example.hw1.data.model.displayName
import com.example.hw1.databinding.ItemJokeBinding

class JokeViewHolder(private val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke, context: Context) {
        bindCategory(joke.category)
        bindQuestion(joke.question)
        bindAnswer(joke.answer)
        bindSource(joke.source, context)
    }

    fun bindCategory(category: String) {
        binding.tvCategory.text = category
    }

    fun bindQuestion(question: String) {
        binding.tvQuestion.text = question
    }

    fun bindAnswer(answer: String) {
        binding.tvAnswer.text = answer
    }

    fun bindSource(source: JokeSource, context: Context) {
        binding.tvSource.text = context.getString(source.displayName)
    }
}
