package com.movies.presentation.details.ui

import androidx.activity.addCallback
import androidx.navigation.fragment.navArgs
import com.movies.R
import com.movies.common.extensions.loadImage
import com.movies.common.extensions.viewBinding
import com.movies.databinding.FragmentDetailsBinding
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.details.ui.ui_state.DetailsUIState
import com.movies.presentation.details.view_model.DetailsViewModel
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<DetailsUIState,DetailsViewModel>() {

    override val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args: DetailsFragmentArgs by navArgs()

    override val layout: Int
        get() = R.layout.fragment_details

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class

    override fun onBind() {
        val movieId = args.MovieId
        viewModel.fetchMovieDetails(movieId)
        navigationListener()
    }

    override fun onDataLoaded(data: DetailsUIState) {
        with(data.movieDetailsData){
            handleFavouriteButton(this)
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

    private fun navigationListener() {
        binding.backImageButton.setOnClickListener {
            viewModel.navigateUp()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.navigateUp()
        }
    }

    private fun handleFavouriteButton(favouriteMovie: MovieUIModel) {
        binding.favouritesToggleButton.setOnClickListener {
            viewModel.updateFavouriteMovieStatus(favouriteMovie)
        }
    }

}