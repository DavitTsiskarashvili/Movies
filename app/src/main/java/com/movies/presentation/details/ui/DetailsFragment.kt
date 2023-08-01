package com.movies.presentation.details.ui

import androidx.activity.addCallback
import androidx.navigation.fragment.navArgs
import com.movies.R
import com.movies.common.extensions.loadImage
import com.movies.common.extensions.viewBinding
import com.movies.databinding.FragmentDetailsBinding
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.details.DetailsFragmentArgs
import com.movies.presentation.details.view_model.DetailsViewModel
import com.movies.presentation.model.movie.MovieUIModel
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<DetailsViewModel>() {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args: DetailsFragmentArgs by navArgs()

    override val layout: Int
        get() = R.layout.fragment_details

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class


    override fun onBind() {
        val model = args.MovieDetails
        navigationListener()
        setMovieDetails()
        handleFavouriteButton(model)
    }

    private fun setMovieDetails() {
        with(args.MovieDetails) {
            with(binding) {
                posterImageView.loadImage(poster)
                movieTitleTextView.text = title
                ratingTextView.text = rating.toString()
                yearTextView.text = releaseDate
                descriptionTextView.text = overview
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