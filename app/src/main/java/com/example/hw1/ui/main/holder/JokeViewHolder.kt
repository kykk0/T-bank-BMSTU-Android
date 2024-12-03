package com.example.hw1.ui.main.holder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.R
import com.example.hw1.data.model.Joke
import com.example.hw1.data.model.JokeSource
import com.example.hw1.databinding.ItemJokeBinding

class JokeViewHolder(private val binding: ItemJokeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(joke: Joke, context: Context) {
        bindCategory(joke.category)
        bindQuestion(joke.question)
        bindAnswer(joke.answer)
        bindSource(joke.source, context)
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

    fun bindSource(source: JokeSource, context: Context) {
        binding.tvSource.text = getLocalizeSource(source, context)
    }

    private fun getLocalizeSource(source: JokeSource, context: Context): String {
        return when (source) {
            JokeSource.LOCAL -> context.getString(R.string.source_local)
            JokeSource.NETWORK -> context.getString(R.string.source_network)
            JokeSource.CACHED -> context.getString(R.string.source_cache)
        }
    }
}
