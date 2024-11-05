package com.example.hw1.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.data.Joke
import com.example.hw1.databinding.ItemJokeBinding
import com.example.hw1.ui.holders.JokeViewHolder
import com.example.hw1.ui.util.JokeDiffUtilCallback
import com.example.hw1.ui.util.JokeDiffUtilCallback.*

class JokeAdapter(
    private val clickListener: (Int) -> Unit,
) : RecyclerView.Adapter<JokeViewHolder>() {

    private var jokes = emptyList<Joke>()

    fun setNewJokes(newJokes: List<Joke>) {
        val diffCallback = JokeDiffUtilCallback(jokes, newJokes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        jokes = newJokes
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        val binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokeViewHolder(binding).apply {
            binding.root.setOnClickListener {
                handleJokeClick(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun onBindViewHolder(
        holder: JokeViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            holder.bind(jokes[position])
        } else {
            payloads.forEach() {
                when (it) {
                    is JokeCategoryPayload -> holder.bindCategory(it.category)
                    is JokeQuestionPayload -> holder.bindQuestion(it.question)
                    is JokeAnswerPayload -> holder.bindAnswer(it.answer)
                }
            }
        }
    }

    override fun getItemCount(): Int = jokes.size

    private fun handleJokeClick(position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            (jokes[position] as? Joke)?.let {
                clickListener(position)
            }
        }
    }
}
