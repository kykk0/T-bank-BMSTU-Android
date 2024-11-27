package com.example.hw1.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.data.model.Joke
import com.example.hw1.databinding.ItemJokeBinding
import com.example.hw1.ui.main.holder.JokeViewHolder
import com.example.hw1.ui.main.util.JokeDiffUtilCallback
import com.example.hw1.ui.main.util.JokeDiffUtilCallback.*

class JokeAdapter(
    private val clickListener: (id: Int) -> Unit,
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
                clickListener(jokes[adapterPosition].id)
            }
        }
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        holder.bind(jokes[position])
    }

    override fun onBindViewHolder(
        holder: JokeViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            holder.bind(jokes[position])
        } else {
            payloads.forEach {
                when (it) {
                    is JokeCategoryPayload -> holder.bindCategory(it.category)
                    is JokeQuestionPayload -> holder.bindQuestion(it.question)
                    is JokeAnswerPayload -> holder.bindAnswer(it.answer)
                    is JokeSourcePayload -> holder.bindSource(it.source)
                }
            }
        }
    }

    override fun getItemCount(): Int = jokes.size
}
