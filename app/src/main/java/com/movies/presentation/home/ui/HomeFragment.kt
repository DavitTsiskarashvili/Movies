package com.movies.presentation.home.ui

import android.annotation.SuppressLint
import androidx.constraintlayout.widget.ConstraintLayout
import com.movies.R
import com.movies.common.extensions.executeScope
import com.movies.common.extensions.hiddenIf
import com.movies.common.extensions.viewBinding
import com.movies.common.extensions.visibleIf
import com.movies.databinding.FragmentHomeBinding
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
    override fun onRefresh() = viewModel.fetchAllMovies()

    override fun onBind() {
        setListeners()
        binding.moviesRecyclerView.adapter = moviePagingAdapter
    }

    override fun onDataLoaded(data: HomeUIState) {
        data.pagingData?.let {
            executeScope {
                moviePagingAdapter.submitData(it)
            }
        }
        data.favouritesData?.let {
            favouriteMovieAdapter.submitList(it)
            handleFavouriteData(it.isNotEmpty())
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setListeners() {
        with(binding) {
            searchAndFilterView.onCategoryButtonClicked {
                viewModel.selectCategory(it)
            }
            // Home button
            navigationButton.leftButtonListener {
                setHomeScreen()
            }
            // Favourite
            //s button
            navigationButton.rightButtonListener {
                setFavouritesScreen()
                viewModel.fetchFavouriteMovies()
                handleFavouriteData(true)
            }
            searchAndFilterView.setOnSearchListener {
                if (searchAndFilterView.queryIsNotBlank() == true) {
                    viewModel.searchMovies(it)
                } else {
                    viewModel.fetchAllMovies()
                }
            }
        }
        with(viewModel) {
            with(moviePagingAdapter) {
                onFavouriteClickListener { favouriteMovie, _ ->
                    updateFavouriteMovieStatus(favouriteMovie)
                }
                onItemClickListener { film ->
                    viewModel.navigateToDetails(film.id)
                }
            }
            with(favouriteMovieAdapter) {
                onFavouriteClickListener { favouriteMovie, _ ->
                    updateFavouriteMovieStatus(favouriteMovie) {
                        fetchFavouriteMovies()
                        favouriteMovieAdapter.notifyDataSetChanged()
                    }
                }
                onItemClickListener { film ->
                    viewModel.navigateToDetails(film.id)
                }
            }
        }
    }

    private fun setHomeScreen() {
        handleHomeScreenComponentsVisibility(true)
        updateRecyclerViewConstraint(false)
        binding.moviesRecyclerView.adapter = moviePagingAdapter
        if (moviePagingAdapter.itemCount == 0) viewModel.fetchAllMovies()
        if (binding.searchAndFilterView.queryIsNotBlank() == true){
            viewModel.searchMovies(binding.searchAndFilterView.searchInput)
        }
    }

    private fun setFavouritesScreen() {
        handleHomeScreenComponentsVisibility(false)
        updateRecyclerViewConstraint(true)
        binding.moviesRecyclerView.adapter = favouriteMovieAdapter
        if (favouriteMovieAdapter.itemCount == 0) viewModel.fetchFavouriteMovies()
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

    private fun updateRecyclerViewConstraint(isFavourite: Boolean){
            with(binding) {
                val params = moviesRecyclerView.layoutParams as ConstraintLayout.LayoutParams
                params.topToBottom = if (isFavourite) favouritesTitleTextView.id else titleTextView.id
                moviesRecyclerView.layoutParams = params
            }
        }

}