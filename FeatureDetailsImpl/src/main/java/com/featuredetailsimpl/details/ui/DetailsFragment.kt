package com.featuredetailsimpl.details.ui

import com.commonpresentation.extensions.loadImage
import com.commonpresentation.extensions.viewBinding
import com.commonpresentation.presentation.base.data.model.MovieUIModel
import com.commonpresentation.presentation.base.fragment.BaseFragment
import com.commonpresentation.utils.NavigationConstants.DETAILS
import com.commonpresentation.utils.NavigationConstants.MOVIE_ID
import com.featuredetailsimpl.R
import com.featuredetailsimpl.databinding.FragmentDetailsBinding
import com.featuredetailsimpl.details.ui.ui_state.DetailsUIState
import com.featuredetailsimpl.details.view_model.DetailsViewModel
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<DetailsUIState, DetailsViewModel>() {

    override val binding by viewBinding(FragmentDetailsBinding::bind)

    override val layout: Int get() = R.layout.fragment_details

    override val viewModelClass: KClass<DetailsViewModel> get() = DetailsViewModel::class

    override fun showBottomView(): Boolean = false

    override fun resultKey() = DETAILS

    override fun onRefresh() = viewModel.fetchMovieDetails(args)

    private val args by lazy { arguments?.getInt(MOVIE_ID)!! }

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