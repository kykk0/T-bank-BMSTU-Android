package com.example.hw1.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw1.databinding.FragmentJokeListBinding
import com.example.hw1.ui.main.adapter.JokeAdapter

class JokeListFragment : Fragment() {

    private var _binding: FragmentJokeListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JokeListViewModel by viewModels()

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
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = jokeAdapter

            viewModel.jokes.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    tvEmptyMessage.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {
                    tvEmptyMessage.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    jokeAdapter.setNewJokes(it)
                }
            }

            viewModel.loading.observe(viewLifecycleOwner) {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }

            btnAddJoke.setOnClickListener {
                val action = JokeListFragmentDirections.actionJokeListFragmentToAddJokeFragment()
                findNavController().navigate(action)
            }
            viewModel.loadJokes()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
