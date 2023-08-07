package com.movies.presentation.home.view_model

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.movies.common.extensions.viewModelScope
import com.movies.common.network.CategoryType
import com.movies.common.utils.LiveDataDelegate
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.usecase.favourites.CheckFavouriteStatusUseCase
import com.movies.domain.usecase.favourites.GetFavouriteMoviesUseCase
import com.movies.domain.usecase.favourites.UpdateFavouriteStatusMovieUseCase
import com.movies.domain.usecase.movies.GetMoviesUseCase
import com.movies.domain.usecase.search.SearchMoviesUseCase
import com.movies.presentation.base.data.MovieUIModel
import com.movies.presentation.base.view_model.BaseViewModel
import com.movies.presentation.home.ui.HomeFragmentDirections
import com.movies.presentation.home.ui.mapper.MovieDomainToUIMapper
import com.movies.presentation.home.ui.mapper.MovieUIToDomainMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val moviesUIMapper: MovieDomainToUIMapper,
    private val movieUIToDomain: MovieUIToDomainMapper,
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
    private val getFavouriteMovies: GetFavouriteMoviesUseCase,
    private val checkFavouriteStatusUseCase: CheckFavouriteStatusUseCase
) : BaseViewModel() {

    val loadingLiveData by LiveDataDelegate<Boolean>()
    val fetchFavouriteMoviesLivedata by LiveDataDelegate<List<MovieUIModel>>()

    private val _fetchMoviesStateFlow = MutableStateFlow<PagingData<MovieUIModel>?>(null)
    val fetchMoviesStateFlow get() = _fetchMoviesStateFlow.asStateFlow()

    private val _categoryStateFlow = MutableStateFlow(CategoryType.POPULAR)
    private val categoryStateFlow = _categoryStateFlow.asStateFlow()

    init {
        startNetworkCall()
    }

    fun startNetworkCall() {
        launchNetwork<Pager<Int, MovieDomainModel>> {
            executeApi {
                val categoryType = categoryStateFlow.value
                moviesUseCase.invoke(categoryType)
            }
            success {
                viewModelScope.launch {
                    it.flow.cachedIn(viewModelScope).collect { pagingData ->
                        val mappedData = pagingData.map {
                            it.isFavourite = checkFavouriteStatusUseCase(it.id)
                            moviesUIMapper(it)
                        }
                        _fetchMoviesStateFlow.emit(mappedData)
                    }
                }
            }
            error {

            }
            loading {

            }
        }
    }

    fun selectCategory(categoryType: CategoryType) {
        _categoryStateFlow.value = categoryType
        startNetworkCall()
    }

    fun searchMovies(query: String) {
        viewModelScope {
            searchMoviesUseCase.invoke(query).flow.cachedIn(viewModelScope)
                .collect { searchedPagingData ->
                    val mappedSearchedData = searchedPagingData.map {
                        it.isFavourite = checkFavouriteStatusUseCase(it.id)
                        moviesUIMapper(it)
                    }
                    _fetchMoviesStateFlow.emit(mappedSearchedData)
                }
        }
    }

    fun fetchFavouriteMovies() {
        viewModelScope {
            fetchFavouriteMoviesLivedata.addValue(moviesUIMapper.mapList(getFavouriteMovies.invoke()))
        }
    }

    fun updateFavouriteMovieStatus(movie: MovieUIModel, callback: (()-> Unit)? = null) {
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
            callback?.invoke()
        }
    }

    fun navigateToDetails(film: MovieUIModel) {
        navigate(HomeFragmentDirections.actionGlobalDetailsFragment(film))
    }

}