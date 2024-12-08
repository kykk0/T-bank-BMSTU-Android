package com.example.hw1.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.example.hw1.domain.entity.Joke
import com.example.hw1.data.entity.JokeSource

class JokeDiffUtilCallback(
    private val oldList: List<Joke>,
    private val newList: List<Joke>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): List<Any>? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        val payloads = mutableListOf<Any>()
        if (oldItem.category != newItem.category) {
            payloads.add(JokeCategoryPayload(newItem.category))
        }
        if (oldItem.question != newItem.question) {
            payloads.add(JokeQuestionPayload(newItem.question))
        }
        if (oldItem.answer != newItem.answer) {
            payloads.add(JokeAnswerPayload(newItem.answer))
        }
        if (oldItem.source != newItem.source) {
            payloads.add(JokeSourcePayload(newItem.source))
        }

        return if (payloads.isEmpty()) null else payloads
    }

    data class JokeCategoryPayload(val category: String)
    data class JokeQuestionPayload(val question: String)
    data class JokeAnswerPayload(val answer: String)
    data class JokeSourcePayload(val source: JokeSource)
}
