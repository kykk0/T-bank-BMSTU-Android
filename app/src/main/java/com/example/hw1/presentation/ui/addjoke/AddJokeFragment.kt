package com.example.hw1.presentation.ui.addjoke

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hw1.R
import com.example.hw1.data.datasource.local.JokeDatabase
import com.example.hw1.data.mapper.JokeMapper
import com.example.hw1.data.repository.JokeRepositoryImpl
import com.example.hw1.databinding.FragmentAddJokeBinding
import com.example.hw1.domain.usecase.AddLocalJokeUseCase

class AddJokeFragment : Fragment() {

    private var _binding: FragmentAddJokeBinding? = null
    private val binding get() = _binding!!

    private val jokeRepository = JokeRepositoryImpl(
        jokeDao = JokeDatabase.INSTANCE.jokeDao(),
        jokeMapper = JokeMapper
    )
    private val viewModel: AddJokeViewModel by viewModels {
        AddJokeViewModelFactory(AddLocalJokeUseCase(jokeRepository))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.addJokeResult.observe(viewLifecycleOwner) { result ->
            if (result == true) {
                Toast.makeText(requireContext(), "Шутка добавлена", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addJokeFragment_to_jokeListFragment)
            }
        }

        with(binding) {
            btnAddJoke.setOnClickListener {
                val category = etCategory.text.toString()
                val question = etQuestion.text.toString()
                val answer = etAnswer.text.toString()

                if (category.isBlank() || question.isBlank() || answer.isBlank()) {
                    Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.addJoke(category, question, answer)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
