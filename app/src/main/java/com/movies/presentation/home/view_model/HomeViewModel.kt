package com.movies.presentation.home.view_model

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.movies.common.extensions.viewModelScope
import com.movies.common.network.CategoryType
import com.movies.common.utils.LiveDataDelegate
import com.movies.domain.usecase.favourites.GetFavouriteMoviesUseCase
import com.movies.domain.usecase.movies.GetMoviesUseCase
import com.movies.domain.usecase.favourites.UpdateFavouriteStatusMovieUseCase
import com.movies.domain.usecase.search.SearchMoviesUseCase
import com.movies.presentation.base.view_model.BaseViewModel
import com.movies.presentation.home.ui.HomeFragmentDirections
import com.movies.presentation.model.mapper.MovieDomainToUIMapper
import com.movies.presentation.model.mapper.MovieUIToDomainMapper
import com.movies.presentation.model.movie.MovieUIModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val moviesUIMapper: MovieDomainToUIMapper,
    private val movieUIToDomain: MovieUIToDomainMapper,
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
    private val getFavouriteMovies: GetFavouriteMoviesUseCase,
) : BaseViewModel() {

    val loadingLiveData by LiveDataDelegate<Boolean>()
    val fetchFavouriteMoviesLivedata by LiveDataDelegate<List<MovieUIModel>>()

    private val _fetchMoviesStateFlow = MutableStateFlow<PagingData<MovieUIModel>?>(null)

    //    val fetchMoviesStateFlow get() = _fetchMoviesStateFlow.asStateFlow()
    val fetchMoviesStateFlow
        get() = _fetchMoviesStateFlow.asLiveData()

    private val _categoryStateFlow = MutableStateFlow(CategoryType.POPULAR)
    val categoryStateFlow = _categoryStateFlow.asStateFlow()

    private val _searchStateFlow = MutableStateFlow<PagingData<MovieUIModel>?>(null)

    //    val searchStateFlow = _searchStateFlow.asStateFlow()
    val searchStateFlow
        get() = _searchStateFlow.asLiveData()

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope {
            loadingLiveData.addValue(true)
            val categoryType = categoryStateFlow.value
            moviesUseCase.invoke(categoryType).flow.cachedIn(viewModelScope)
                .collect { pagingData ->
                    val mappedData = pagingData.map {
                        moviesUIMapper(it)
                    }
                    _fetchMoviesStateFlow.emit(mappedData)
                }
            loadingLiveData.addValue(false)
        }
    }

    fun selectCategory(categoryType: CategoryType) {
        _categoryStateFlow.value = categoryType
        getMovies()
//        viewModelScope {
//            _categoryStateFlow.emit(categoryType)
//        }
    }

    fun searchMovies(query: String) {
        viewModelScope {
            searchMoviesUseCase.invoke(query).flow.cachedIn(viewModelScope)
                .collect { searchedPagingData ->
                    val mappedSearchedData = searchedPagingData.map {
                        moviesUIMapper(it)
                    }
                    _searchStateFlow.emit(mappedSearchedData)
                }
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