package com.movies.presentation.details.ui

import com.movies.R
import com.movies.common.extensions.loadImage
import com.movies.common.extensions.viewBinding
import com.movies.databinding.FragmentDetailsBinding
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.details.ui.ui_state.DetailsUIState
import com.movies.presentation.details.view_model.DetailsViewModel
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<DetailsUIState, DetailsViewModel>() {

    override val binding by viewBinding(FragmentDetailsBinding::bind)
    override val layout: Int get() = R.layout.fragment_details
    override val viewModelClass: KClass<DetailsViewModel> get() = DetailsViewModel::class

    private val args by lazy { arguments?.getInt("movieId")!! }

    override fun onRefresh() = viewModel.fetchMovieDetails(args)

    override fun onBind() {
        viewModel.fetchMovieDetails(args)
        navigationListener()
    }

    override fun onDataLoaded(data: DetailsUIState) {
        handleFavouriteButton(data.movieDetailsData)
        setMovieDetailsViews(data)
    }

    private fun setMovieDetailsViews(data: DetailsUIState) {
        with(data.movieDetailsData) {
            with(binding) {
                posterImageView.loadImage(poster)
                movieTitleTextView.text = title
                ratingTextView.text = rating.toString()
                yearTextView.text = releaseDate
                descriptionTextView.text = overview
                categoryTextView.text = genreString
                durationTextView.text = duration
                favouritesToggleButton.isChecked = isFavourite
            }
        }
    }

    private fun handleFavouriteButton(favouriteMovie: MovieUIModel) {
        binding.favouritesToggleButton.setOnClickListener {
            viewModel.updateFavouriteMovieStatus(favouriteMovie)
        }
    }

    private fun navigationListener() {
        binding.backImageButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

}