package com.example.hw1.presentation.ui.jokelist

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
import com.example.hw1.R
import com.example.hw1.databinding.FragmentJokeListBinding
import com.example.hw1.domain.usecase.GetLocalJokesUseCase
import com.example.hw1.domain.usecase.InitializeUseCase
import com.example.hw1.domain.usecase.LoadCachedJokesUseCase
import com.example.hw1.domain.usecase.LoadMoreJokesUseCase
import com.example.hw1.presentation.ui.jokelist.adapter.JokeAdapter
import com.example.hw1.data.datasource.local.JokeDatabase
import com.example.hw1.data.mapper.JokeMapper
import com.example.hw1.data.repository.JokeRepositoryImpl

class JokeListFragment : Fragment() {

    private var _binding: FragmentJokeListBinding? = null
    private val binding get() = _binding!!

    private val jokeRepository = JokeRepositoryImpl(
        jokeDao = JokeDatabase.INSTANCE.jokeDao(),
        jokeMapper = JokeMapper
    )

    private val viewModel: JokeListViewModel by viewModels {
        JokeListViewModelFactory(
            GetLocalJokesUseCase(jokeRepository),
            LoadMoreJokesUseCase(jokeRepository),
            LoadCachedJokesUseCase(jokeRepository),
            InitializeUseCase(jokeRepository)
        )
    }
    private lateinit var jokeAdapter: JokeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJokeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        jokeAdapter = JokeAdapter({ jokeId ->
            val action =
                JokeListFragmentDirections.actionJokeListFragmentToJokeDetailsFragment(jokeId)
            findNavController().navigate(action)
        }, requireContext())

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
                        R.string.can_add_new_joke,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    jokeAdapter.setNewJokes(it)
                }
            }

            viewModel.errorMessage.observe(viewLifecycleOwner) { messageKey ->
                when (messageKey) {
                    "ERROR_INTERNET" -> {
                        Toast.makeText(
                            requireContext(),
                            R.string.error_internet,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    "ERROR_LOADING_JOKES" -> {
                        Toast.makeText(
                            requireContext(),
                            R.string.error_loading_jokes,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
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
