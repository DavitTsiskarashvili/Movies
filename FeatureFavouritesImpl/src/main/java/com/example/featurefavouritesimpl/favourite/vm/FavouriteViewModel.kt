package com.example.featurefavouritesimpl.favourite.vm

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.commonpresentation.extensions.viewModelScope
import com.commonpresentation.mapper.movie.MovieDomainToUIMapper
import com.commonpresentation.mapper.movie.MovieUIToDomainMapper
import com.commonpresentation.presentation.base.data.model.MovieUIModel
import com.commonpresentation.presentation.base.data.ui_state.UIState
import com.commonpresentation.presentation.base.view_model.BaseViewModel
import com.commonpresentation.utils.NavigationConstants
import com.favouritesdomain.usecase.FetchFavouriteMoviesUseCase
import com.favouritesdomain.usecase.UpdateFavouriteStatusMovieUseCase
import com.featuredetailsapi.navigation.DetailsNavigationApi
import com.homeapi.navigation.HomeNavigationApi

class FavouriteViewModel(
    private val getFavouriteMovies: FetchFavouriteMoviesUseCase,
    private val movieUIToDomain: MovieUIToDomainMapper,
    private val moviesDomainToUIMapper: MovieDomainToUIMapper,
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
    private val detailsNavigationApi: DetailsNavigationApi,
    private val homeNavigationApi: HomeNavigationApi
) : BaseViewModel<List<MovieUIModel>>() {

    val moviesLiveData: MutableLiveData<MovieUIModel> = MutableLiveData()

    override fun onCreate() {
        viewModelScope {
            fetchFavouriteMovies()
        }
    }

    suspend fun fetchFavouriteMovies() {
        _uiStateLiveData.postValue(
            UIState.Success(moviesDomainToUIMapper.mapList(getFavouriteMovies.invoke()))
        )
    }

    fun updateFavouriteMovieStatus(movie: MovieUIModel) {
        viewModelScope {
            updateMovieStatus.invoke(movieUIToDomain(movie))
            fetchFavouriteMovies()
        }
    }

    fun navigateToDetails(item: Int) {
        val bundle = Bundle().apply {
            putInt(NavigationConstants.MOVIE_ID, item)
        }
        detailsNavigationApi.navigateToDetails(bundle)
    }

    fun navigateToHome(){
        homeNavigationApi.navigateToHome()
    }

}