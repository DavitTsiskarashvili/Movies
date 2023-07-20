package com.movies.presentation.home.ui

import androidx.core.view.isVisible
import com.movies.R
import com.movies.common.extensions.hiddenIf
import com.movies.common.extensions.observeLiveData
import com.movies.common.extensions.viewBinding
import com.movies.common.extensions.visibleIf
import com.movies.databinding.FragmentHomeBinding
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.home.adapter.MovieAdapter
import com.movies.presentation.home.view_model.HomeViewModel
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeViewModel>() {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val movieAdapter by lazy {
        MovieAdapter()
    }

    override val layout: Int
        get() = R.layout.fragment_home

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    override fun onBind() {
        initRecycler()
        observe()
        setListeners()
        searchMovies()
    }

    private fun initRecycler() {
        viewModel.getMovies()
        binding.moviesRecyclerView.adapter = movieAdapter
    }

    private fun observe() {
        observeLiveData(viewModel.loadingLiveData) {
            binding.progressBar.isVisible = it
        }
        observeLiveData(viewModel.fetchMoviesLiveData) { movies ->
            handleData(movies.isNotEmpty())
            movieAdapter.submitList(movies)
        }
        observeLiveData(viewModel.searchMoviesLiveData) {searchedMovies ->
            handleData(searchedMovies.isNotEmpty())
            movieAdapter.submitList(searchedMovies)
        }
    }

    private fun handleData(isLoaded: Boolean) {
        with(binding) {
            errorStateView.hiddenIf(isLoaded)
            moviesRecyclerView.visibleIf(isLoaded)
        }
    }

    private fun setListeners() {
        filterMovies()
        refresh()
        homeListener()
        favouritesListener()
    }

    private fun filterMovies() {
        binding.searchAndFilterView.categoryButtonListener {
            viewModel.selectCategory(it.categoryType)
        }
    }

    private fun refresh() {
        binding.errorStateView.refreshButtonListener {
            viewModel.getMovies()
        }
    }

    private fun homeListener() {
        binding.navigationView.homeButtonListener {
            handleBottomNavigation(false)
        }
    }

    private fun favouritesListener() {
        binding.navigationView.favouritesButtonListener {
            handleBottomNavigation(true)
        }
    }

    private fun handleBottomNavigation(isClicked: Boolean) {
        with(binding) {
            moviesRecyclerView.hiddenIf(isClicked)
            searchAndFilterView.hiddenIf(isClicked)
            titleTextView.hiddenIf(isClicked)
            favouritesTitleTextView.visibleIf(isClicked)
            emptyListImageView.visibleIf(isClicked)
            emptyListTextView.visibleIf(isClicked)
        }
    }

    private fun searchMovies() {
        with(binding.searchAndFilterView) {
            searchListener { searchInput ->
                viewModel.searchMovies(query = searchInput)
            } else {
                handleData(true)
            }
        }
    }

}