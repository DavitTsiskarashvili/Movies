package com.movies.presentation.favourite.ui

import com.movies.R
import com.movies.common.extensions.changeScreen
import com.movies.common.extensions.hideKeyboard
import com.movies.common.extensions.observeLiveData
import com.movies.common.extensions.viewBinding
import com.movies.common.extensions.visibleIf
import com.movies.databinding.FragmentFavouritesBinding
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.details.ui.DetailsFragment
import com.movies.presentation.favourite.ui.adapter.FavouriteMovieAdapter
import com.movies.presentation.favourite.vm.FavouriteViewModel
import com.movies.presentation.utils.NavigationConstants.DETAILS
import com.movies.presentation.utils.NavigationConstants.FAVOURITES
import com.movies.presentation.view.navigation.NavigationButtons

class FavouriteFragment : BaseFragment<List<MovieUIModel>, FavouriteViewModel>() {

    override val viewModelClass = FavouriteViewModel::class

    override val binding by viewBinding(FragmentFavouritesBinding::bind)

    override val layout = R.layout.fragment_favourites

    override fun activeNavigationButton(): NavigationButtons = NavigationButtons.RIGHT_BUTTON

    override fun resultKey(): String = FAVOURITES

    override fun defaultLeftButtonAction() {
        parentFragmentManager.popBackStack()
    }

    override fun needPressBack(): Boolean = false

    private lateinit var favouriteMovieAdapter: FavouriteMovieAdapter

    override fun onDataLoaded(data: List<MovieUIModel>) {
        favouriteMovieAdapter.submitList(data)
        binding.emptyListImageView.visibleIf(data.isEmpty())
        binding.emptyListTextView.visibleIf(data.isEmpty())
    }

    override fun onBind() {
        handleResult(DETAILS){ viewModel.onCreate() }
        initRecyclerView()
        observer()
    }

    private fun observer() {
        observeLiveData(viewModel.moviesLiveData) { viewModel.updateFavouriteMovieStatus(it) }
    }

    private fun initRecyclerView() {
        favouriteMovieAdapter = FavouriteMovieAdapter(
            onClickCallback = { film ->
                hideKeyboard()
                changeScreen(DetailsFragment(), film.id)
            },
            onFavouriteClick = { favouriteMovie ->
                viewModel.moviesLiveData.value = favouriteMovie
            }
        )
        binding.favouriteMoviesAdapter.adapter = favouriteMovieAdapter
    }

}