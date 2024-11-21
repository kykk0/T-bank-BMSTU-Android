package com.example.hw1.ui.main.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.data.model.Joke
import com.example.hw1.databinding.ItemJokeBinding

class JokeViewHolder(private val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke) {
        bindCategory(joke.category)
        bindQuestion(joke.question)
        bindAnswer(joke.answer)
    }

    fun bindCategory(category: String){
        binding.tvCategory.text = category
    }

    fun bindQuestion(question: String){
        binding.tvQuestion.text = question
    }

    fun bindAnswer(answer: String){
        binding.tvAnswer.text = answer
    }
}
