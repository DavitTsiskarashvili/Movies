package com.example.featurefavouritesimpl.favourite.ui

import com.commonpresentation.extensions.changeScreen
import com.commonpresentation.extensions.executeScope
import com.commonpresentation.extensions.hideKeyboard
import com.commonpresentation.extensions.hideViews
import com.commonpresentation.extensions.observeLiveData
import com.commonpresentation.extensions.showViews
import com.commonpresentation.extensions.viewBinding
import com.commonpresentation.presentation.base.data.model.MovieUIModel
import com.commonpresentation.presentation.base.fragment.BaseFragment
import com.commonpresentation.presentation.view.navigation.NavigationButtons
import com.commonpresentation.utils.NavigationConstants.DETAILS
import com.commonpresentation.utils.NavigationConstants.FAVOURITES
import com.example.featurefavouritesimpl.R
import com.example.featurefavouritesimpl.databinding.FragmentFavouritesBinding
import com.movies.presentation.details.ui.DetailsFragment
import com.example.featurefavouritesimpl.favourite.ui.adapter.FavouriteMovieAdapter
import com.example.featurefavouritesimpl.favourite.vm.FavouriteViewModel

class FavouriteFragment : BaseFragment<List<MovieUIModel>, FavouriteViewModel>() {

    override val viewModelClass = FavouriteViewModel::class

    override val binding by viewBinding(FragmentFavouritesBinding::bind)

    override val layout = R.layout.fragment_favourites

    override fun activeNavigationButton(): NavigationButtons = NavigationButtons.RIGHT_BUTTON

    override fun resultKey(): String = FAVOURITES

    override fun defaultLeftButtonAction() {
        parentFragmentManager.popBackStack()
    }

    override fun needPressBack(): Boolean = true

    private lateinit var favouriteMovieAdapter: FavouriteMovieAdapter

    override fun onDataLoaded(data: List<MovieUIModel>) {
        favouriteMovieAdapter.submitList(data)
        with(binding){
            if (data.isEmpty()) showViews(emptyListImageView, emptyListTextView)
            else hideViews(emptyListTextView, emptyListImageView)
        }
    }

    override fun onBind() {
        handleResult(DETAILS) { executeScope { viewModel.fetchFavouriteMovies() } }
        initRecyclerView()
        observer()
    }

    private fun observer() {
        observeLiveData(viewModel.moviesLiveData) { viewModel.updateFavouriteMovieStatus(it) }
    }

    private fun initRecyclerView() {
        favouriteMovieAdapter = FavouriteMovieAdapter(
            onClickCallback = { film ->
                context?.hideKeyboard()
                changeScreen(DetailsFragment(), film.id)
            },
            onFavouriteClick = { favouriteMovie ->
                viewModel.moviesLiveData.value = favouriteMovie
            }
        )
        binding.favouriteMoviesAdapter.adapter = favouriteMovieAdapter
    }

}