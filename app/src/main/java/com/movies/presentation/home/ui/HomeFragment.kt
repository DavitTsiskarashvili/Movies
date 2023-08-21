package com.movies.presentation.home.ui

import androidx.constraintlayout.widget.ConstraintLayout
import com.movies.R
import com.movies.common.extensions.changeScreen
import com.movies.common.extensions.executeScope
import com.movies.common.extensions.hiddenIf
import com.movies.common.extensions.viewBinding
import com.movies.common.extensions.visibleIf
import com.movies.databinding.FragmentHomeBinding
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.details.ui.DetailsFragment
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
    var favouritesIsUpdated = false
    override fun onRefresh() = viewModel.fetchAllMovies()

    override fun onBind() {
        setListeners()
        binding.moviesRecyclerView.adapter = moviePagingAdapter
    }

    override fun onDataLoaded(data: HomeUIState) {
        data.pagingData?.let {
            executeScope { moviePagingAdapter.submitData(it) }
        }
        data.favouritesData?.let {
            favouriteMovieAdapter.submitList(it)
            handleFavouriteData(it.isNotEmpty())
        }
    }

    private fun setListeners() {
        with(binding) {
            searchAndFilterView.onCategoryButtonClicked {
                viewModel.selectCategory(it)
            }
            // Navigate to Home Screen
            navigationButton.leftButtonListener {
                setHomeScreen()
            }
            // Navigate to Favourites Screen
            navigationButton.rightButtonListener {
                setFavouritesScreen()
                handleFavouriteData(true)
            }
            // Search
            searchAndFilterView.setOnSearchListener {
                viewModel.searchMovies(it)
            }
            // Cancel Search
            searchAndFilterView.searchCancelListener {
                viewModel.fetchAllMovies()
            }
        }
        with(viewModel) {
            // Add or Remove from favourites
            moviePagingAdapter.onFavouriteClickListener { favouriteMovie, _ ->
                updateFavouriteMovieStatus(favouriteMovie)
            }
            // Navigate to Details Screen
            moviePagingAdapter.onItemClickListener { film ->
                changeScreen(DetailsFragment(), film.id)
            }
            // Add or Remove from favourites
            favouriteMovieAdapter.onFavouriteClickListener { favouriteMovie, _ ->
                updateFavouriteMovieStatus(favouriteMovie) {
                    fetchFavouriteMovies()
                }
            }
            // Navigate to Details Screen
            favouriteMovieAdapter.onItemClickListener { film ->
                changeScreen(DetailsFragment(), film.id)
            }
        }
    }

    private fun setHomeScreen() {
        handleHomeScreenComponentsVisibility(true)
        updateRecyclerViewConstraint(false)
        binding.moviesRecyclerView.adapter = moviePagingAdapter
    }

    private fun setFavouritesScreen() {
        handleHomeScreenComponentsVisibility(false)
        updateRecyclerViewConstraint(true)
        binding.moviesRecyclerView.adapter = favouriteMovieAdapter
        if (favouriteMovieAdapter.itemCount == 0 || favouritesIsUpdated) viewModel.fetchFavouriteMovies()
        favouritesIsUpdated = false
    }

    private fun handleHomeScreenComponentsVisibility(isHomeSelected: Boolean) {
        with(binding) {
            searchAndFilterView.visibleIf(isHomeSelected)
            titleTextView.visibleIf(isHomeSelected)
            favouritesTitleTextView.hiddenIf(isHomeSelected)
        }
    }

    private fun handleFavouriteData(isLoaded: Boolean) {
        with(binding) {
            emptyListTextView.hiddenIf(isLoaded)
            emptyListImageView.hiddenIf(isLoaded)
            moviesRecyclerView.visibleIf(isLoaded)
        }
    }

    private fun updateRecyclerViewConstraint(isFavourite: Boolean) {
        with(binding) {
            val params = moviesRecyclerView.layoutParams as ConstraintLayout.LayoutParams
            params.topToBottom = if (isFavourite) favouritesTitleTextView.id else titleTextView.id
            moviesRecyclerView.layoutParams = params
        }
    }

}