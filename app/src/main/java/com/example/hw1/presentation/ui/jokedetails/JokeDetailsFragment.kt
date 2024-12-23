package com.example.hw1.presentation.ui.jokedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hw1.R
import com.example.hw1.data.datasource.local.JokeDatabase
import com.example.hw1.domain.entity.Joke
import com.example.hw1.data.entity.displayName
import com.example.hw1.data.mapper.JokeMapper
import com.example.hw1.data.repository.JokeRepositoryImpl
import com.example.hw1.databinding.FragmentJokeDetailsBinding
import com.example.hw1.domain.usecase.FindJokeByIdUseCase

class JokeDetailsFragment : Fragment() {

    private var _binding: FragmentJokeDetailsBinding? = null
    private val binding get() = _binding!!
    private val jokeRepository = JokeRepositoryImpl(
        jokeDao = JokeDatabase.INSTANCE.jokeDao(),
        jokeMapper = JokeMapper
    )

    private val viewModel: JokeDetailsViewModel by viewModels {
        JokeDetailsViewModelFactory(FindJokeByIdUseCase(jokeRepository))
    }
    private val args: JokeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectJokeById(args.jokeId)
        viewModel.selectedJoke.observe(viewLifecycleOwner) { joke ->
            joke?.let { setupJokeData(it) }
        }
    }

    private fun setupJokeData(joke: Joke) {
        with(binding) {
            tvCategory.text = getString(R.string.joke_category, joke.category)
            tvQuestion.text = getString(R.string.joke_question, joke.question)
            tvAnswer.text = getString(R.string.joke_answer, joke.answer)
            tvSource.text = getString(R.string.joke_source, getString(joke.source.displayName))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
