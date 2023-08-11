package com.movies.presentation.favourite.ui

import com.movies.R
import com.movies.common.extensions.changeScreen
import com.movies.common.extensions.viewBinding
import com.movies.common.extensions.visibleIf
import com.movies.databinding.FavouriteLayoutBinding
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.details.ui.DetailsFragment
import com.movies.presentation.favourite.ui.adapter.FavouriteMovieAdapter
import com.movies.presentation.favourite.vm.FavouriteViewModel
import com.movies.presentation.home.ui.HomeFragment
import com.movies.presentation.view.navigation.NavigationButtons

class FavouriteFragment : BaseFragment<List<MovieUIModel>, FavouriteViewModel>() {

    override val viewModelClass = FavouriteViewModel::class
    override val binding by viewBinding(FavouriteLayoutBinding::bind)
    override val layout = R.layout.favourite_layout

    private val favouriteMovieAdapter by lazy { FavouriteMovieAdapter() }

    override fun onRefresh() = viewModel.fetchFavouriteMovies()

    override fun onDataLoaded(data: List<MovieUIModel>) {
        favouriteMovieAdapter.submitList(data)
        binding.emptyListImageView.visibleIf(data.isEmpty())
        binding.emptyListTextView.visibleIf(data.isEmpty())
    }

    override fun onBind() {
        binding.favouriteMoviesAdapter.adapter = favouriteMovieAdapter
        setListeners()
    }

    private fun setListeners() {
        binding.navigationButton.setButtonsActiveStatus(NavigationButtons.RIGHT_BUTTON)
        favouriteMovieAdapter.onFavouriteClickListener { favouriteMovie, _ ->
            viewModel.updateFavouriteMovieStatus(favouriteMovie) {
                viewModel.fetchFavouriteMovies()
            }
        }
        // Navigate to Details Screen
        favouriteMovieAdapter.onItemClickListener { film ->
            changeScreen(DetailsFragment(), film.id)
        }
        // Navigate to Home Screen
        binding.navigationButton.leftButtonListener {
            changeScreen(HomeFragment(), null)
        }
    }

}