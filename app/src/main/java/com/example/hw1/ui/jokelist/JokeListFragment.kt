package com.example.hw1.ui.jokelist

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    if (viewModel.canLoadMore() && lastVisibleItem >= totalItemCount - 1) {
                        viewModel.loadMoreJokes()
                    }
                }
            })

            viewModel.jokes.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Можете добавить свою шутку",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    jokeAdapter.setNewJokes(it)
                }
            }

            viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
                if (message.isNotEmpty()) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }

            viewModel.isLoading.observe(viewLifecycleOwner) {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }

            btnAddJoke.setOnClickListener {
                val action = JokeListFragmentDirections.actionJokeListFragmentToAddJokeFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
