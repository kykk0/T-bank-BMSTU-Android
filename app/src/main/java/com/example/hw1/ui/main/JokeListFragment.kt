package com.example.hw1.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw1.R
import com.example.hw1.databinding.FragmentJokeListBinding
import com.example.hw1.ui.main.adapters.JokeAdapter

class JokeListFragment : Fragment() {

    private var _binding: FragmentJokeListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: JokeViewModel
    private val jokeAdapter = JokeAdapter { jokeId ->
        val action = JokeListFragmentDirections.actionJokeListFragmentToJokeDetailsFragment(jokeId)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[JokeViewModel::class.java]
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = jokeAdapter

        viewModel.jokes.observe(viewLifecycleOwner) { jokeAdapter.setNewJokes(it) }
        viewModel.loadJokes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
