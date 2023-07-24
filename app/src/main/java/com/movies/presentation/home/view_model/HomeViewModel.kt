package com.movies.presentation.home.view_model

import androidx.lifecycle.MutableLiveData
import com.movies.common.extensions.viewModelScope
import com.movies.common.network.CategoryType
import com.movies.common.utils.LiveDataDelegate
import com.movies.domain.usecase.movies.DeleteFavouriteMovieUseCase
import com.movies.domain.usecase.movies.GetFavouriteMoviesUseCase
import com.movies.domain.usecase.movies.GetMoviesUseCase
import com.movies.domain.usecase.movies.InsertFavouriteMovieUseCase
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
    private val insertMovie: InsertFavouriteMovieUseCase,
    private val deleteMovie: DeleteFavouriteMovieUseCase,
    private val getFavouriteMovies: GetFavouriteMoviesUseCase,
) : BaseViewModel() {

    val fetchMoviesLiveData by LiveDataDelegate<List<MovieUIModel>>()
    val loadingLiveData by LiveDataDelegate<Boolean>()
    val searchMoviesLiveData by LiveDataDelegate<List<MovieUIModel>>()
    val fetchFavouriteMoviesLivedata by LiveDataDelegate<List<MovieUIModel>>()
    private val categoryStateLiveData = MutableLiveData(CategoryType.POPULAR)
    private val selectedMovieLiveData = MutableLiveData<MovieUIModel>()


    fun getMovies() {
        viewModelScope {
            loadingLiveData.addValue(true)
            fetchMoviesLiveData.addValue(
                moviesUIMapper.mapList(
                    moviesUseCase.invoke(
                        categoryStateLiveData.value
                    )
                )
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

    fun insertFavouriteMovie(movie: MovieUIModel){
        viewModelScope {
            insertMovie.invoke(movieUIToDomain(movie))
        }
    }

    fun deleteFavouriteMovie(movie: MovieUIModel){
        viewModelScope {
            deleteMovie.invoke(movieUIToDomain(movie))
        }
    }

    fun fetchFavouriteMovies(){
        viewModelScope {
            fetchFavouriteMoviesLivedata.addValue(moviesUIMapper.mapList(getFavouriteMovies.invoke()))
        }
    }

    fun onMovieItemClick(film: MovieUIModel) {
        selectedMovieLiveData.postValue(film)
    }

    fun navigateToDetails(film: MovieUIModel){
        navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(film))
    }

}