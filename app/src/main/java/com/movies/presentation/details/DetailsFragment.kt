package com.movies.presentation.details

import androidx.activity.addCallback
import androidx.navigation.fragment.navArgs
import com.movies.R
import com.movies.common.extensions.loadImage
import com.movies.common.extensions.viewBinding
import com.movies.databinding.FragmentDetailsBinding
import com.movies.presentation.base.fragment.BaseFragment
import kotlin.reflect.KClass

class DetailsFragment : BaseFragment<DetailsViewModel>() {

    private val binding by viewBinding(FragmentDetailsBinding::bind)
    private val args: DetailsFragmentArgs by navArgs()

    override val layout: Int
        get() = R.layout.fragment_details

    override val viewModelClass: KClass<DetailsViewModel>
        get() = DetailsViewModel::class

    override fun onBind() {
        navigationListener()
        setMovieDetails()
    }

    private fun setMovieDetails(){
        with(args.MovieDetails){
            with(binding){
                posterImageView.loadImage(poster)
                movieTitleTextView.text = title
                ratingTextView.text = rating.toString()
                yearTextView.text = releaseDate
                descriptionTextView.text = overview
//                categoryTextView.text =
            }
        }
    }

    private fun navigationListener(){
        binding.backImageButton.setOnClickListener {
            viewModel.navigateUp()
        }
        requireActivity().onBackPressedDispatcher.addCallback {
            viewModel.navigateUp()
        }
    }

}