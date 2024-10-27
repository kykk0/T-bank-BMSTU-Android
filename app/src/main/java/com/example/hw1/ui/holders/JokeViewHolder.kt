package com.example.hw1.ui.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.data.Joke
import com.example.hw1.databinding.ItemJokeBinding

class JokeViewHolder(private val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        binding.tvCategory.text = joke.category
        binding.tvQuestion.text = joke.question
        binding.tvAnswer.text = joke.answer
    }
}
