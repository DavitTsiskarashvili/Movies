package com.movies.presentation.home.ui

import com.movies.R
import com.movies.common.extensions.changeScreen
import com.movies.common.extensions.executeScope
import com.movies.common.extensions.viewBinding
import com.movies.databinding.FragmentHomeBinding
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.details.ui.DetailsFragment
import com.movies.presentation.favourite.ui.FavouriteFragment
import com.movies.presentation.home.ui.adapter.movie.MoviePagingAdapter
import com.movies.presentation.home.ui.ui_state.HomeUIState
import com.movies.presentation.home.view_model.HomeViewModel
import com.movies.presentation.view.navigation.NavigationButtons
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeUIState, HomeViewModel>() {

    override val binding by viewBinding(FragmentHomeBinding::bind)
    private val moviePagingAdapter by lazy { MoviePagingAdapter() }
    override val layout: Int = R.layout.fragment_home
    override val viewModelClass: KClass<HomeViewModel> get() = HomeViewModel::class
    override fun onRefresh() = viewModel.fetchAllMovies()

    override fun onBind() {
        setListeners()
        binding.moviesRecyclerView.adapter = moviePagingAdapter
    }

    override fun onDataLoaded(data: HomeUIState) {
        data.pagingData?.let {
            executeScope { moviePagingAdapter.submitData(it) }
        }
    }

    private fun setListeners() {
        with(binding) {
            searchAndFilterView.onCategoryButtonClicked {
                viewModel.selectCategory(it)
            }
            navigationButton.setButtonsActiveStatus(NavigationButtons.LEFT_BUTTON)
            navigationButton.rightButtonListener {
                changeScreen(FavouriteFragment(), null)
            }
            searchAndFilterView.setOnSearchListener {
                viewModel.searchMovies(it)
            }
            searchAndFilterView.searchCancelListener {
                viewModel.fetchAllMovies()
            }
        }
        moviePagingAdapter.onFavouriteClickListener { favouriteMovie, _ ->
            viewModel.updateFavouriteMovieStatus(favouriteMovie)
        }
        moviePagingAdapter.onItemClickListener { film ->
            changeScreen(DetailsFragment(), film.id)
        }
    }
}