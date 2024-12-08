package com.example.hw1.presentation.ui.jokelist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hw1.domain.entity.Joke
import com.example.hw1.databinding.ItemJokeBinding
import com.example.hw1.presentation.ui.jokelist.holder.JokeViewHolder
import com.example.hw1.presentation.util.JokeDiffUtilCallback
import com.example.hw1.presentation.util.JokeDiffUtilCallback.*

class JokeAdapter(
    private val clickListener: (id: Int) -> Unit,
    private val context: Context
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
        holder.bind(jokes[position], context)
    }

    override fun onBindViewHolder(
        holder: JokeViewHolder,
        position: Int,
        payloads: MutableList<Any>,
    ) {
        if (payloads.isEmpty()) {
            holder.bind(jokes[position], context)
        } else {
            payloads.forEach {
                when (it) {
                    is JokeCategoryPayload -> holder.bindCategory(it.category)
                    is JokeQuestionPayload -> holder.bindQuestion(it.question)
                    is JokeAnswerPayload -> holder.bindAnswer(it.answer)
                    is JokeSourcePayload -> holder.bindSource(it.source, context)
                }
            }
        }
    }

    override fun getItemCount(): Int = jokes.size
}
