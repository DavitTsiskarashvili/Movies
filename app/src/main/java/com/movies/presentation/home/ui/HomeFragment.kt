package com.movies.presentation.home.ui

import com.movies.R
import com.movies.common.extensions.changeScreen
import com.movies.common.extensions.executeScope
import com.movies.common.extensions.hideKeyboard
import com.movies.common.extensions.viewBinding
import com.movies.databinding.FragmentHomeBinding
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.details.ui.DetailsFragment
import com.movies.presentation.favourite.ui.FavouriteFragment
import com.movies.presentation.home.ui.adapter.MoviePagingAdapter
import com.movies.presentation.home.ui.ui_state.HomeUIState
import com.movies.presentation.home.view_model.HomeViewModel
import com.movies.presentation.utils.NavigationConstants.DETAILS
import com.movies.presentation.utils.NavigationConstants.FAVOURITES
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
                changeScreen(DetailsFragment(), film.id)
            },
            onFavouriteClick = { favouriteMovie, _ ->
                viewModel.updateFavouriteMovieStatus(favouriteMovie)
            }
        )
        binding.moviesRecyclerView.adapter = moviePagingAdapter
    }

}