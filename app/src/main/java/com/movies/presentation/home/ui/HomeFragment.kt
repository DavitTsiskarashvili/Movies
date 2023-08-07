package com.movies.presentation.home.ui

import androidx.lifecycle.lifecycleScope
import com.movies.R
import com.movies.common.extensions.collectLatestInLifecycle
import com.movies.common.extensions.hiddenIf
import com.movies.common.extensions.invisibleIf
import com.movies.common.extensions.observeLiveData
import com.movies.common.extensions.viewBinding
import com.movies.common.extensions.visibleIf
import com.movies.databinding.FragmentHomeBinding
import com.movies.presentation.base.data.MovieUIModel
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.home.ui.adapter.favourite.FavouriteMovieAdapter
import com.movies.presentation.home.ui.adapter.movie.MoviePagingAdapter
import com.movies.presentation.home.view_model.HomeViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeViewModel>() {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val moviePagingAdapter by lazy {
        MoviePagingAdapter()
    }

    private val favouriteMovieAdapter by lazy {
        FavouriteMovieAdapter()
    }

    override val layout: Int
        get() = R.layout.fragment_home

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    override fun onBind() {
        initHomeRecycler()
        observe()
        setUpNavigation()
        setListeners()
        searchMovies()

    }

    private fun initHomeRecycler() {
        binding.moviesRecyclerView.adapter = moviePagingAdapter
    }

    private fun initFavouriteRecycler(list: List<MovieUIModel>) {
        binding.moviesRecyclerView.adapter = favouriteMovieAdapter
        favouriteMovieAdapter.submitList(list)
    }

    private fun observe() {
        viewModel.fetchMoviesStateFlow.collectLatestInLifecycle(viewLifecycleOwner) {
            handleDataVisibility(true)
            it?.let {
                moviePagingAdapter.submitData(it)
            }
        }

        viewModel.searchStateFlow.collectLatestInLifecycle(viewLifecycleOwner) {
            handleDataVisibility(true)
            it.let {
                if (it != null) {
                    moviePagingAdapter.submitData(it)
                }
            }
        }

        observeLiveData(viewModel.fetchFavouriteMoviesLivedata) { favouriteMovies ->
            handleFavouriteData(favouriteMovies.isNotEmpty())
            binding.moviesRecyclerView.adapter = favouriteMovieAdapter
            lifecycleScope.launch {
                initFavouriteRecycler(favouriteMovies)
            }
        }

        observeLiveData(viewModel.loadingLiveData) {

        }

    }

    private fun handleDataVisibility(isLoaded: Boolean) {
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
        setUpNavigation()
    }

    private fun filterMovies() {
        binding.searchAndFilterView.categoryButtonListener {
            viewModel.selectCategory(it)
        }
    }

    private fun refresh() {
        binding.errorStateView.refreshButtonListener {
            viewModel.startNetworkCall()
        }
    }

    private fun homeListener() {
        with(binding) {
            navigationButton.firstButtonListener {
                handleBottomNavigation(false)
                handleSearch(searchAndFilterView.searchInput)
                initHomeRecycler()
            }
        }
    }

    private fun favouritesListener() {
        binding.navigationButton.secondButtonListener {
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
            handleDataVisibility(true)
            viewModel.startNetworkCall()
        }
    }

    private fun handleFavouriteButton() {
        moviePagingAdapter.onFavouriteClickListener { favouriteMovie, _ ->
            viewModel.updateFavouriteMovieStatus(favouriteMovie)

        }
    }

    private fun setUpNavigation() {
        moviePagingAdapter.onItemClickListener { film ->
            viewModel.navigateToDetails(film)
        }
        favouriteMovieAdapter.onItemClickListener { film ->
            viewModel.navigateToDetails(film)
        }
    }

}