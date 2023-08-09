package com.movies.presentation.home.view_model

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.movies.common.extensions.viewModelScope
import com.movies.common.network.CategoryType
import com.movies.domain.model.GenreDomainModel
import com.movies.domain.model.MovieDomainModel
import com.movies.domain.usecase.favourites.CheckFavouriteStatusUseCase
import com.movies.domain.usecase.favourites.GetFavouriteMoviesUseCase
import com.movies.domain.usecase.favourites.UpdateFavouriteStatusMovieUseCase
import com.movies.domain.usecase.movies.GetGenresUseCase
import com.movies.domain.usecase.movies.GetMoviesUseCase
import com.movies.domain.usecase.search.SearchMoviesUseCase
import com.movies.presentation.base.data.model.MovieUIModel
import com.movies.presentation.base.data.ui_state.UIState
import com.movies.presentation.base.view_model.BaseViewModel
import com.movies.presentation.home.ui.HomeFragmentDirections
import com.movies.presentation.home.ui.mapper.movie.MovieDomainToUIMapper
import com.movies.presentation.home.ui.mapper.movie.MovieUIToDomainMapper
import com.movies.presentation.home.ui.ui_state.HomeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val moviesUIMapper: MovieDomainToUIMapper,
    private val movieUIToDomain: MovieUIToDomainMapper,
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
    private val getFavouriteMovies: GetFavouriteMoviesUseCase,
    private val checkFavouriteStatusUseCase: CheckFavouriteStatusUseCase,
    private val genresUseCase: GetGenresUseCase
) : BaseViewModel<HomeUIState>() {

    //    val loadingLiveData by LiveDataDelegate<Boolean>()
//    val fetchFavouriteMoviesLivedata by LiveDataDelegate<List<MovieUIModel>>()
//
//    private val _fetchMoviesStateFlow = MutableStateFlow<PagingData<MovieUIModel>?>(null)
//    val fetchMoviesStateFlow get() = _fetchMoviesStateFlow.asStateFlow()
//
    private val categoryStateFlow = MutableStateFlow(CategoryType.POPULAR)
    private val genreMap = mutableMapOf<Int, String>()

    override fun onCreate() {
        fetchMovieGenre()
    }

    private fun fetchMovieGenre() {
        launchNetwork<List<GenreDomainModel>> {
            executeApi {
                genresUseCase.invoke()
            }
            success {
                it.forEach { genre ->
                    genreMap[genre.id] = genre.name
                }
                startNetworkCall()
            }
            error {
                _uiStateFlow.emit(UIState.Error(it))
            }
            loading {
                if (it) {
                    _uiStateFlow.emit(UIState.Loading)
                }
            }
        }
    }

    fun startNetworkCall() {
        launchNetwork<Pager<Int, MovieDomainModel>> {
            loading {
                if (it) _uiStateFlow.emit(UIState.Loading)
            }
            executeApi {
                val categoryType = categoryStateFlow.value
                moviesUseCase.invoke(categoryType)
            }
            success {
                handleSuccessCase(it.flow)
            }
            error {
                _uiStateFlow.emit(UIState.Error(it))
            }

        }
    }

    private suspend fun handleSuccessCase(flow: Flow<PagingData<MovieDomainModel>>) {
        viewModelScope.launch {
            flow.cachedIn(viewModelScope).collect { pagingData ->
                val mappedData = pagingData.map {
                    with(it) {
                        isFavourite = checkFavouriteStatusUseCase(id)
                        genreString = genreMap[genreInt]
                    }
                    moviesUIMapper(it)
                }
                _uiStateFlow.emit(UIState.Success(HomeUIState(pagingData = mappedData)))
            }
        }
    }

    fun selectCategory(categoryType: CategoryType) {
        categoryStateFlow.value = categoryType
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
                    _uiStateFlow.emit(UIState.Success(HomeUIState(pagingData = mappedSearchedData)))
                }
        }
    }

    fun fetchFavouriteMovies() {
        viewModelScope {
            _uiStateFlow.emit(
                UIState.Success(
                    HomeUIState(
                        favouritesData = moviesUIMapper.mapList(
                            getFavouriteMovies.invoke()
                        )
                    )
                )
            )
        }
    }

    fun updateFavouriteMovieStatus(movie: MovieUIModel, callback: (() -> Unit)? = null) {
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
            callback?.invoke()
        }
    }

    fun navigateToDetails(film: MovieUIModel) {
        navigate(HomeFragmentDirections.actionGlobalDetailsFragment(film))
    }

}