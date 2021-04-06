package com.deverick.marvelousheroes.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deverick.marvelousheroes.databinding.FragmentCharactersBinding
import com.deverick.marvelousheroes.ui.adapters.CharactersAdapter
import com.deverick.marvelousheroes.utils.Resource
import com.deverick.marvelousheroes.viewmodels.CharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private lateinit var charactersAdapter: CharactersAdapter
    private val viewModel: CharactersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupNavigation()
        setupRecyclerView()

        charactersAdapter.setOnItemClickListener {
            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToDetailsFragment(
                    it
                )
            )
        }

        binding.btnRetry.setOnClickListener {
            viewModel.getCharacters()
        }

        binding.searchIcon.setOnClickListener {
            findNavController().navigate(
                CharactersFragmentDirections.actionCharactersFragmentToSearchFragment()
            )
        }

        viewModel.characters.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Loading -> {
                    showProgressBar()
                }

                is Resource.Success -> {
                    hideProgressBar()
                    hideErrorMessage()

                    response.data?.let { marvelResponse ->
                        charactersAdapter.differ.submitList(marvelResponse.data.results)

                        val totalPages = marvelResponse.data.total

                        isLastPage = viewModel.charactersPage == totalPages.toInt()

                        if (isLastPage) {
                            binding.rvCharacters.setPadding(0, 0, 0, 0)
                        }
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occurred: $message", Toast.LENGTH_LONG)
                            .show()
                        showErrorMessage(message)
                    }
                }
            }
        })
    }


    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideErrorMessage() {
        binding.textErrorMessage.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMessage(message: String) {
        binding.textErrorMessage.visibility = View.VISIBLE
        binding.textErrorMessage.text = message

        isError = true
    }

    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNoErrors = !isError
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 20
            val shouldPaginate =
                isNoErrors && isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                        isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                viewModel.getCharacters()
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setupNavigation() {
        val navController = findNavController()
        val configuration = AppBarConfiguration(navController.graph, binding.drawerLayout)

        binding.drawerView.setupWithNavController(navController)
        binding.toolbar.setupWithNavController(navController, configuration)
        binding.toolbar.title = null
    }

    private fun setupRecyclerView() {
        charactersAdapter = CharactersAdapter()

        binding.rvCharacters.apply {
            adapter = charactersAdapter
            layoutManager = GridLayoutManager(activity, 2)
            addOnScrollListener(scrollListener)
        }
    }
}
