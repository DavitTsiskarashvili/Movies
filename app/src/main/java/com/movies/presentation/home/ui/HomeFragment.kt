package com.movies.presentation.home.ui

import android.annotation.SuppressLint
import com.movies.R
import com.movies.common.extensions.executeScope
import com.movies.common.extensions.hiddenIf
import com.movies.common.extensions.invisibleIf
import com.movies.common.extensions.viewBinding
import com.movies.common.extensions.visibleIf
import com.movies.databinding.FragmentHomeBinding
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.home.ui.adapter.favourite.FavouriteMovieAdapter
import com.movies.presentation.home.ui.adapter.movie.MoviePagingAdapter
import com.movies.presentation.home.ui.ui_state.HomeUIState
import com.movies.presentation.home.view_model.HomeViewModel
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeUIState, HomeViewModel>() {

    override val binding by viewBinding(FragmentHomeBinding::bind)

    private val moviePagingAdapter by lazy { MoviePagingAdapter() }

    private val favouriteMovieAdapter by lazy { FavouriteMovieAdapter() }

    override val layout: Int get() = R.layout.fragment_home

    override val viewModelClass: KClass<HomeViewModel> get() = HomeViewModel::class

    override fun onBind() {
        initHomeRecycler()
        setUpNavigation()
        setListeners()
        searchMovies()
    }

    override fun onDataLoaded(data: HomeUIState) {
        data.pagingData?.let {
            handleDataVisibility()
            executeScope {
                moviePagingAdapter.submitData(it)
            }
        }
        data.favouritesData?.let {
            handleFavouriteData(it.isNotEmpty())
            binding.moviesRecyclerView.adapter = favouriteMovieAdapter
            initFavouriteRecycler(it)
        }
    }

    private fun initHomeRecycler() {
        binding.moviesRecyclerView.adapter = moviePagingAdapter
    }

    private fun initFavouriteRecycler(list: List<MovieUIModel>) {
        binding.moviesRecyclerView.adapter = favouriteMovieAdapter
        favouriteMovieAdapter.submitList(list)
    }

    private fun setListeners() {
        filterMovies()
        refresh()
        navigateToHomeListener()
        navigateToFavouritesListener()
        handleFavouriteButton()
        setUpNavigation()
        cancelSearch()
    }

    private fun handleDataVisibility() {
        with(binding) {
            errorStateView.hiddenIf(true)
            moviesRecyclerView.visibleIf(true)
        }
    }

    private fun handleFavouriteData(isLoaded: Boolean) {
        with(binding) {
            emptyListTextView.hiddenIf(isLoaded)
            emptyListImageView.hiddenIf(isLoaded)
            moviesRecyclerView.visibleIf(isLoaded)
        }
    }

    private fun filterMovies() {
        binding.searchAndFilterView.categoryButtonListener {
            viewModel.selectCategory(it)
        }
    }

    private fun refresh() {
        binding.errorStateView.refreshButtonListener {
            viewModel.fetchMovies()
        }
    }

    private fun navigateToHomeListener() {
        with(binding) {
            navigationButton.leftButtonListener {
                viewModel.fetchMovies()
                handleBottomNavigationVisibility(false)
                handleSearch(searchAndFilterView.searchInput)
                initHomeRecycler()
            }
        }
    }

    private fun navigateToFavouritesListener() {
        binding.navigationButton.rightButtonListener {
            handleBottomNavigationVisibility(true)
            viewModel.fetchFavouriteMovies()
        }
    }

    private fun handleBottomNavigationVisibility(isClicked: Boolean) {
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
            handleDataVisibility()
        }
    }

    private fun cancelSearch() {
        binding.searchAndFilterView.clearSearchInput {
            viewModel.fetchMovies()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleFavouriteButton() {
        with(viewModel) {
            moviePagingAdapter.onFavouriteClickListener { favouriteMovie, _ ->
                updateFavouriteMovieStatus(favouriteMovie)
            }
            favouriteMovieAdapter.onFavouriteClickListener { favouriteMovie, _ ->
                updateFavouriteMovieStatus(favouriteMovie) {
                    fetchFavouriteMovies()
                    favouriteMovieAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setUpNavigation() {
        moviePagingAdapter.onItemClickListener { film ->
            viewModel.navigateToDetails(film.id)
        }
        favouriteMovieAdapter.onItemClickListener { film ->
            viewModel.navigateToDetails(film.id)
        }
    }

}