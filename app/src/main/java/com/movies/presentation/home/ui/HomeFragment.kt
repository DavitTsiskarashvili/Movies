package com.movies.presentation.home.ui

import androidx.core.view.isVisible
import com.movies.R
import com.movies.common.extensions.hiddenIf
import com.movies.common.extensions.invisibleIf
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
        observeLiveData(viewModel.searchMoviesLiveData) { searchedMovies ->
            handleData(searchedMovies.isNotEmpty())
            movieAdapter.submitList(searchedMovies)
        }
        observeLiveData(viewModel.fetchFavouriteMoviesLivedata) { favouriteMovies ->
            handleFavouriteData(favouriteMovies.isNotEmpty())
            movieAdapter.submitList(favouriteMovies)
        }
    }

    private fun handleData(isLoaded: Boolean) {
        with(binding) {
            errorStateView.hiddenIf(isLoaded)
            moviesRecyclerView.visibleIf(isLoaded)
        }
    }

    private fun handleFavouriteData(isLoaded: Boolean) {
        with(binding) {
            emptyListTextView.hiddenIf(isLoaded)
            emptyListImageView.hiddenIf(isLoaded)
            moviesRecyclerView.visibleIf(isLoaded)
        }
    }

    private fun setListeners() {
        filterMovies()
        refresh()
        homeListener()
        favouritesListener()
        handleFavouriteButton()
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
            handleSearch(binding.searchAndFilterView.searchInput)
        }
    }

    private fun favouritesListener() {
        binding.navigationView.favouritesButtonListener {
            handleBottomNavigation(true)
            viewModel.fetchFavouriteMovies()
        }
    }

    private fun handleBottomNavigation(isClicked: Boolean) {
        with(binding) {
            moviesRecyclerView.hiddenIf(isClicked)
            searchAndFilterView.hiddenIf(isClicked)
            titleTextView.invisibleIf(isClicked)
            favouritesTitleTextView.visibleIf(isClicked)
            emptyListImageView.visibleIf(isClicked)
            emptyListTextView.visibleIf(isClicked)
        }
    }

    private fun searchMovies() {
        with(binding.searchAndFilterView) {
            searchListener {
                handleSearch(searchInput)
            }
        }
    }

    private fun handleSearch(searchInput: String) {
        if (searchInput.isNotEmpty()) {
            viewModel.searchMovies(query = searchInput)
        } else {
            handleData(true)
            viewModel.getMovies()
        }
    }

    private fun handleFavouriteButton() {
        movieAdapter.onFavouriteClickListener { favouriteMovie, isClicked ->
            if (isClicked) {
                viewModel.insertFavouriteMovie(favouriteMovie)
            } else {
                viewModel.deleteFavouriteMovie(favouriteMovie)
            }
        }
    }
}