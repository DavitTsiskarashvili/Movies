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
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.home.ui.adapter.favourite.FavouriteMovieAdapter
import com.movies.presentation.home.ui.adapter.movie.MoviePagingAdapter
import com.movies.presentation.home.view_model.HomeViewModel
import com.movies.presentation.base.data.MovieUIModel
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

    private fun observeDefaultMovies(){
        viewModel.fetchMoviesStateFlow.collectLatestInLifecycle(viewLifecycleOwner) {
            handleDataVisibility(true)
            it?.let {
                moviePagingAdapter.submitData(it)
            }
        }
    }

    private fun observe() {
        observeDefaultMovies()

        viewModel.searchStateFlow.collectLatestInLifecycle(viewLifecycleOwner) {
            handleDataVisibility(true)
            it?.let {
                moviePagingAdapter.submitData(it)
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

    private fun setListeners() {
        filterMovies()
        refresh()
        navigateToHomeListener()
        navigateToFavouritesListener()
        handleFavouriteButton()
        setUpNavigation()
        cancelSearch()
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

    private fun navigateToHomeListener() {
        with(binding) {
            navigationButton.firstButtonListener {
                handleBottomNavigationVisibility(false)
                handleSearch(searchAndFilterView.searchInput)
                initHomeRecycler()
            }
        }
    }

    private fun navigateToFavouritesListener() {
        binding.navigationButton.secondButtonListener {
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
            handleDataVisibility(true)
        }
    }

    private fun cancelSearch(){
        binding.searchAndFilterView.clearSearchInput {
            observeDefaultMovies()
        }
    }

    private fun handleFavouriteButton() {
        with(viewModel){
            moviePagingAdapter.onFavouriteClickListener { favouriteMovie, _ ->
                updateFavouriteMovieStatus(favouriteMovie)
            }
            favouriteMovieAdapter.onFavouriteClickListener { favouriteMovie, _ ->
                updateFavouriteMovieStatus(favouriteMovie)
                fetchFavouriteMovies()
            }
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