package com.example.hw1.ui.add_joke

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.hw1.R
import com.example.hw1.databinding.FragmentAddJokeBinding

class AddJokeFragment : Fragment() {

    private var _binding: FragmentAddJokeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddJokeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddJoke.setOnClickListener {
            val category = binding.etCategory.text.toString()
            val question = binding.etQuestion.text.toString()
            val answer = binding.etAnswer.text.toString()

            if (category.isBlank() || question.isBlank() || answer.isBlank()) {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addJoke(category, question, answer)
                Toast.makeText(requireContext(), "Шутка добавлена", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addJokeFragment_to_jokeListFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
