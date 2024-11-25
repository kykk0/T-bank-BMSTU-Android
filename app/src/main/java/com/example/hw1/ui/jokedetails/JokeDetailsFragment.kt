package com.example.hw1.ui.jokedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.hw1.R
import com.example.hw1.data.model.Joke
import com.example.hw1.data.model.JokeSource
import com.example.hw1.databinding.FragmentJokeDetailsBinding

class JokeDetailsFragment : Fragment() {

    private var _binding: FragmentJokeDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JokeDetailsViewModel by viewModels()
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
            tvSource.text = getString(R.string.joke_source, getLocalizedSource(joke.source))
        }
    }

    private fun getLocalizedSource(source: JokeSource): String {
        return when (source) {
            JokeSource.LOCAL -> getString(R.string.source_local)
            JokeSource.NETWORK -> getString(R.string.source_network)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
