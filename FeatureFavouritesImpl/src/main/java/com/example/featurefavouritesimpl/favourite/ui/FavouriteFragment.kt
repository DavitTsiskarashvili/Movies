package com.example.featurefavouritesimpl.favourite.ui

import com.commonpresentation.extensions.hideKeyboard
import com.commonpresentation.extensions.hideViews
import com.commonpresentation.extensions.observeLiveData
import com.commonpresentation.extensions.showViews
import com.commonpresentation.extensions.viewBinding
import com.commonpresentation.presentation.base.data.model.MovieUIModel
import com.commonpresentation.presentation.base.fragment.BaseFragment
import com.commonpresentation.presentation.view.navigation.NavigationButtons
import com.example.featurefavouritesimpl.R
import com.example.featurefavouritesimpl.databinding.FragmentFavouritesBinding
import com.example.featurefavouritesimpl.favourite.ui.adapter.FavouriteMovieAdapter
import com.example.featurefavouritesimpl.favourite.vm.FavouriteViewModel

class FavouriteFragment : BaseFragment<List<MovieUIModel>, FavouriteViewModel>() {

    override val viewModelClass = FavouriteViewModel::class

    override val binding by viewBinding(FragmentFavouritesBinding::bind)

    override val layout = R.layout.fragment_favourites

    private lateinit var favouriteMovieAdapter: FavouriteMovieAdapter

    override fun onDataLoaded(data: List<MovieUIModel>) {
        favouriteMovieAdapter.submitList(data)
        with(binding) {
            if (data.isEmpty()) showViews(emptyListImageView, emptyListTextView)
            else hideViews(emptyListTextView, emptyListImageView)
        }
    }

    override fun onBind() {
        initRecyclerView()
        observer()
        setListeners()
    }

    private fun observer() {
        observeLiveData(viewModel.moviesLiveData) { viewModel.updateFavouriteMovieStatus(it) }
    }

    private fun setListeners() = with(binding.navigationView) {
        setButtonsActiveStatus(NavigationButtons.RIGHT_BUTTON)
        leftButtonListener { viewModel.navigateToHome() }
    }

    private fun initRecyclerView() {
        favouriteMovieAdapter = FavouriteMovieAdapter(
            onClickCallback = { film ->
                context?.hideKeyboard()
                viewModel.navigateToDetails(film.id)
            },
            onFavouriteClick = { favouriteMovie ->
                viewModel.moviesLiveData.value = favouriteMovie
            }
        )
        binding.favouriteMoviesRecyclerView.adapter = favouriteMovieAdapter
    }

}