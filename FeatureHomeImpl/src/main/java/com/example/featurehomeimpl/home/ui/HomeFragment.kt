package com.example.featurehomeimpl.home.ui

import com.commonpresentation.extensions.changeScreen
import com.commonpresentation.extensions.executeScope
import com.commonpresentation.extensions.hideKeyboard
import com.commonpresentation.extensions.viewBinding
import com.commonpresentation.presentation.base.fragment.BaseFragment
import com.commonpresentation.utils.NavigationConstants.DETAILS
import com.commonpresentation.utils.NavigationConstants.FAVOURITES
import com.example.featurehomeimpl.R
import com.example.featurehomeimpl.databinding.FragmentHomeBinding
import com.example.featurehomeimpl.home.ui.adapter.MoviePagingAdapter
import com.example.featurehomeimpl.home.ui.ui_state.HomeUIState
import com.example.featurehomeimpl.home.view_model.HomeViewModel
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeUIState, HomeViewModel>() {

    override val binding by viewBinding(FragmentHomeBinding::bind)

    override val layout: Int = R.layout.fragment_home

    override val viewModelClass: KClass<HomeViewModel> get() = HomeViewModel::class

    override fun onRefresh() = viewModel.fetchAllMovies()

    override fun defaultRightButtonAction() {
        changeScreen(FavouriteFragment(), null)
    }

    override fun onDataLoaded(data: HomeUIState) {
        data.pagingData?.let {
            executeScope { moviePagingAdapter.submitData(it) }
        }
    }

    private lateinit var moviePagingAdapter: MoviePagingAdapter

    override fun onBind() {
        handleResult(DETAILS) {
//            viewModel.fetchAllMovies()
        }
        handleResult(FAVOURITES) {
//            viewModel.fetchAllMovies()
        }
        initRecyclerView()
        setListeners()
        binding.searchAndFilterView.emptySearchCallback = { viewModel.fetchAllMovies() }
    }

    private fun setListeners() {
        with(binding) {
            searchAndFilterView.onCategoryButtonClicked {
                searchAndFilterView.clearFocus()
                viewModel.selectCategory(it)
            }
            searchAndFilterView.setOnSearchListener {
                viewModel.searchMovies(it)
            }
            root.setOnClickListener {
                requireContext().hideKeyboard()
                searchAndFilterView.clearFocus()
            }
        }
    }

    private fun initRecyclerView() {
        moviePagingAdapter = MoviePagingAdapter(
            onClickCallback = { film ->
                requireContext().hideKeyboard()
                binding.searchAndFilterView.clearFocus()
                viewModel.navigateToDetails(film.id)
//                changeScreen(DetailsFragment(), film.id)
            },
            onFavouriteClick = { favouriteMovie, _ ->
                viewModel.updateFavouriteMovieStatus(favouriteMovie)
            }
        )
        binding.moviesRecyclerView.adapter = moviePagingAdapter
    }

}