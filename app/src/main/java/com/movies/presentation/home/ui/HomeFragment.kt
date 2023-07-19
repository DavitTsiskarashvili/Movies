package com.movies.presentation.home.ui

import androidx.core.view.isVisible
import com.movies.R
import com.movies.common.extensions.observeLiveData
import com.movies.common.extensions.viewBinding
import com.movies.common.network.Category
import com.movies.databinding.FragmentHomeBinding
import com.movies.presentation.base.fragment.BaseFragment
import com.movies.presentation.home.adapter.MovieAdapter
import com.movies.presentation.home.view_model.HomeViewModel
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeViewModel>() {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val movieAdapter by lazy {
        MovieAdapter()
    }

    override val layout: Int
        get() = R.layout.fragment_home

    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    override fun onBind() {
        initRecycler()
        observe()
        filterMovies()
    }

    private fun initRecycler() {
        viewModel.getMovies(Category.POPULAR)
        binding.moviesRecyclerView.adapter = movieAdapter
    }

    private fun observe() {
        observeLiveData(viewModel.loadingLiveData) {
            binding.progressBar.isVisible = it
        }
        observeLiveData(viewModel.fetchMoviesLiveData) { movies ->
            movieAdapter.submitList(movies)
        }
    }

    private fun filterMovies() {
        with(binding.searchAndFilterView) {
            categorySelectedListener(Category.POPULAR) {
                viewModel.getMovies(it)
            }
            categorySelectedListener(Category.TOP_RATED) {
                viewModel.getMovies(it)
            }
        }
    }

}