package com.example.hw1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.data.Joke
import com.example.hw1.databinding.ItemJokeBinding
import com.example.hw1.ui.holders.JokeViewHolder
import com.example.hw1.ui.util.JokeDiffUtilCallback

class JokeAdapter(private var jokes: List<Joke>) : RecyclerView.Adapter<JokeViewHolder>() {

    fun updateJokes(newJokes: List<Joke>) {
        val diffCallback = JokeDiffUtilCallback(jokes, newJokes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        jokes = newJokes
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun getItemCount(): Int = jokes.size
}
