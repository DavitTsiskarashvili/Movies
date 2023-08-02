package com.movies.presentation.home.ui

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.movies.R
import com.movies.common.extensions.hiddenIf
import com.movies.common.extensions.invisibleIf
import com.movies.common.extensions.observeLiveData
import com.movies.common.extensions.viewBinding
import com.movies.common.extensions.visibleIf
import com.movies.databinding.FragmentHomeBinding
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.home.adapter.MoviePagingAdapter
import com.movies.presentation.home.view_model.HomeViewModel
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeViewModel>() {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val moviePagingAdapter by lazy {
        MoviePagingAdapter()
    }

    override val layout: Int
        get() = R.layout.fragment_home

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    override fun onBind() {
//        LoaderDialog(requireContext()).apply { initiateDialog(true) }
        initRecycler()
        observe()
        setUpNavigation()
        setListeners()
        searchMovies()
    }

    private fun initRecycler() {
        binding.moviesRecyclerView.adapter = moviePagingAdapter
    }

    private fun observe() {
        observeLiveData(viewModel.loadingLiveData) {}
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovies().collect{
                handleData(true)
                moviePagingAdapter.submitData(it)
                Log.d("bachi", "$it")
            }
        }

//        observeLiveData(viewModel.fetchMoviesLiveData) { movies ->
//            handleData(true)
//            lifecycleScope.launch {
//                moviePagingAdapter.submitData(movies)
//                Log.d("bachi", "$movies")
//            }
//        }


//        observeLiveData(viewModel.searchMoviesLiveData) { searchedMovies ->
//            handleData(searchedMovies.isNotEmpty())
//            lifecycleScope.launch {
//                moviePagingAdapter.submitData(searchedMovies)
//            }
//        }
//        observeLiveData(viewModel.fetchFavouriteMoviesLivedata) { favouriteMovies ->
//            handleFavouriteData(favouriteMovies.isNotEmpty())
//            lifecycleScope.launch {
//                moviePagingAdapter.submitData(favouriteMovies)
//            }
//        }
    }

    private fun handleData(isLoaded: Boolean) {
        with(binding) {
            errorStateView.hiddenIf(isLoaded)
            moviesRecyclerView.visibleIf(isLoaded)
        }
    }

    private fun handleFavouriteData(isLoaded: Boolean) {
        with(binding) {
            emptyListTextView.hiddenIf(isLoaded)
            emptyListImageView.hiddenIf(isLoaded)
            moviesRecyclerView.visibleIf(isLoaded)
        }
    }

    private fun setListeners() {
        filterMovies()
        refresh()
        homeListener()
        favouritesListener()
        handleFavouriteButton()
        setUpNavigation()
    }

    private fun filterMovies() {
        binding.searchAndFilterView.categoryButtonListener {
            viewModel.selectCategory(it.categoryType)
        }
    }

    private fun refresh() {
        binding.errorStateView.refreshButtonListener {
            viewModel.getMovies()
        }
    }

    private fun homeListener() {
        with(binding){
            navigationButton.firstButtonListener {
                handleBottomNavigation(false)
                handleSearch(searchAndFilterView.searchInput)
            }
        }
    }

    private fun favouritesListener() {
        binding.navigationButton.secondButtonListener {
            handleBottomNavigation(true)
            viewModel.fetchFavouriteMovies()
        }
    }

    private fun handleBottomNavigation(isClicked: Boolean) {
        with(binding) {
            moviesRecyclerView.hiddenIf(isClicked)
            searchAndFilterView.hiddenIf(isClicked)
            titleTextView.invisibleIf(isClicked)
            favouritesTitleTextView.visibleIf(isClicked)
            emptyListImageView.visibleIf(isClicked)
            emptyListTextView.visibleIf(isClicked)
        }
    }

    private fun searchMovies() {
        with(binding.searchAndFilterView) {
            searchListener {
                handleSearch(searchInput)

            }
        }
    }

    private fun handleSearch(searchInput: String) {
        if (searchInput.isNotEmpty()) {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.searchMovies(searchInput).collect{
                    moviePagingAdapter.submitData(it)
                }
            }
//            viewModel.searchMovies(query = searchInput)
        } else {
            handleData(true)
            viewModel.getMovies()
        }
    }

    private fun handleFavouriteButton() {
        moviePagingAdapter.onFavouriteClickListener { favouriteMovie, _ ->
                viewModel.updateFavouriteMovieStatus(favouriteMovie)

        }
    }

    private fun setUpNavigation(){
        moviePagingAdapter.onItemClickListener { film ->
            viewModel.navigateToDetails(film)
        }
    }

}