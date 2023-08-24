package com.example.featurehomeimpl.home.view_model

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.commondomain.model.MovieDomainModel
import com.commonpresentation.extensions.viewModelScope
import com.commonpresentation.mapper.movie.MovieDomainToUIMapper
import com.commonpresentation.mapper.movie.MovieUIToDomainMapper
import com.commonpresentation.presentation.base.data.model.MovieUIModel
import com.commonpresentation.presentation.base.data.ui_state.UIState
import com.commonpresentation.presentation.base.view_model.BaseViewModel
import com.commonpresentation.utils.NavigationConstants.MOVIE_ID
import com.example.featurehomeimpl.home.ui.ui_state.HomeUIState
import com.example.featurehomeimpl.view.search_and_filter.adapter.model.CategoryType
import com.favouritesdomain.usecase.CheckFavouriteStatusUseCase
import com.favouritesdomain.usecase.UpdateFavouriteStatusMovieUseCase
import com.featuredetailsapi.navigation.DetailsNavigationApi
import com.featurefavouritesapi.navigation.FavouritesNavigationApi
import com.homedomain.model.GenreDomainModel
import com.homedomain.usecase.movies.FetchGenresUseCase
import com.homedomain.usecase.movies.FetchMoviesUseCase
import com.homedomain.usecase.search.SearchMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel(
    private val moviesUseCase: FetchMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val moviesUIMapper: MovieDomainToUIMapper,
    private val checkFavouriteStatusUseCase: CheckFavouriteStatusUseCase,
    private val genresUseCase: FetchGenresUseCase,
    private val movieUIToDomain: MovieUIToDomainMapper,
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
    private val detailsNavigationApi: DetailsNavigationApi,
    private val favouritesNavigationApi: FavouritesNavigationApi
) : BaseViewModel<HomeUIState>() {

    private val categoryStateFlow = MutableStateFlow(CategoryType.POPULAR.value)
    private val genreMap = mutableMapOf<Int, String>()

    override fun onCreate() {
        fetchAllMovies()
    }

    fun fetchAllMovies() {
        if (genreMap.isEmpty()) fetchMovieGenre() else fetchMovies()
    }

    fun selectCategory(categoryType: String) {
        categoryStateFlow.value = categoryType.lowercase()
        fetchMovieGenre()
    }

    private fun fetchMovieGenre() {
        launchNetwork<List<GenreDomainModel>> {
            executeApi { genresUseCase.invoke() }

            success {
                it.forEach { genre ->
                    genreMap[genre.id] = genre.name
                }
                fetchMovies()
            }

            error { _uiStateLiveData.postValue(UIState.Error(it)) }

            loading { if (it) _uiStateLiveData.postValue(UIState.Loading) }
        }
    }

    private fun fetchMovies() {
        launchNetwork<Pager<Int, MovieDomainModel>> {
            executeApi {
                val categoryType = categoryStateFlow.value
                moviesUseCase.invoke(categoryType)
            }

            loading { if (it) _uiStateLiveData.postValue(UIState.Loading) }

            success { handleSuccessCase(it.flow) }

            error { _uiStateLiveData.postValue(UIState.Error(it)) }
        }
    }

    fun searchMovies(query: String) {
        launchNetwork<Pager<Int, MovieDomainModel>> {
            executeApi { searchMoviesUseCase.invoke(query) }

            success { handleSuccessCase(it.flow) }

            error { _uiStateLiveData.postValue(UIState.Error(it)) }
        }
    }

    private fun handleSuccessCase(flow: Flow<PagingData<MovieDomainModel>>) {
        viewModelScope {
            flow.cachedIn(viewModelScope).collect { pagingData ->
                val mappedData = pagingData.map {
                    with(it) {
                        isFavourite = checkFavouriteStatusUseCase(id)
                        genreString = genreMap[genreInt]
                    }
                    moviesUIMapper(it)
                }
                _uiStateLiveData.postValue(UIState.Success(HomeUIState(pagingData = mappedData)))
            }
        }
    }

    fun updateFavouriteMovieStatus(movie: MovieUIModel) {
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
        }
    }

    fun navigateToDetails(item: Int) {
        val bundle = Bundle().apply {
            putInt(MOVIE_ID, item)
        }
        detailsNavigationApi.navigateToDetails(bundle)
    }

    fun navigateToFavourites() {
        favouritesNavigationApi.navigateToFavourites()
    }

}