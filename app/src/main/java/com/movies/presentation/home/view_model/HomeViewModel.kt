package com.movies.presentation.home.view_model

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.map
import com.movies.common.extensions.viewModelScope
import com.movies.common.network.CategoryType
import com.movies.common.utils.LiveDataDelegate
import com.movies.domain.usecase.movies.GetFavouriteMoviesUseCase
import com.movies.domain.usecase.movies.GetMoviesUseCase
import com.movies.domain.usecase.movies.UpdateFavouriteStatusMovieUseCase
import com.movies.domain.usecase.search.SearchMoviesUseCase
import com.movies.presentation.base.view_model.BaseViewModel
import com.movies.presentation.home.ui.HomeFragmentDirections
import com.movies.presentation.model.mapper.MovieDomainToUIMapper
import com.movies.presentation.model.mapper.MovieUIToDomainMapper
import com.movies.presentation.model.movie.MovieUIModel

class HomeViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val moviesUIMapper: MovieDomainToUIMapper,
    private val movieUIToDomain: MovieUIToDomainMapper,
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
    private val getFavouriteMovies: GetFavouriteMoviesUseCase,
) : BaseViewModel() {

    val fetchMoviesLiveData by LiveDataDelegate<PagingData<MovieUIModel>>()
    val loadingLiveData by LiveDataDelegate<Boolean>()
    val searchMoviesLiveData by LiveDataDelegate<List<MovieUIModel>>()
    val fetchFavouriteMoviesLivedata by LiveDataDelegate<List<MovieUIModel>>()
    private val categoryStateLiveData = MutableLiveData(CategoryType.POPULAR)

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope {
            loadingLiveData.addValue(true)
            fetchMoviesLiveData.addValue(
                moviesUseCase.invoke(categoryStateLiveData.value).value?.map {
                    moviesUIMapper(it)
                } ?: throw RuntimeException()
            )
            loadingLiveData.addValue(false)
        }
    }

    fun selectCategory(categoryType: CategoryType) {
        categoryStateLiveData.value = categoryType
        getMovies()
    }

    fun searchMovies(query: String) {
        viewModelScope {
            searchMoviesLiveData.addValue(moviesUIMapper.mapList(searchMoviesUseCase.invoke(query)))
        }
    }

    fun updateFavouriteMovieStatus(movie: MovieUIModel) {
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
        }
    }

    fun fetchFavouriteMovies() {
        viewModelScope {
            fetchFavouriteMoviesLivedata.addValue(moviesUIMapper.mapList(getFavouriteMovies.invoke()))
        }
    }

    fun navigateToDetails(film: MovieUIModel) {
        navigate(HomeFragmentDirections.actionGlobalDetailsFragment(film))
    }

}