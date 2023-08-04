package com.movies.presentation.home.view_model

import android.util.Log.d
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.movies.common.extensions.viewModelScope
import com.movies.common.network.CategoryType
import com.movies.common.utils.LiveDataDelegate
import com.movies.data.remote.network.NetworkLauncherApi
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
import com.movies.presentation.view.loader.LoaderDialog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.java.KoinJavaComponent.get

class HomeViewModel(
    private val moviesUseCase: GetMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val moviesUIMapper: MovieDomainToUIMapper,
    private val movieUIToDomain: MovieUIToDomainMapper,
    private val updateMovieStatus: UpdateFavouriteStatusMovieUseCase,
    private val getFavouriteMovies: GetFavouriteMoviesUseCase,
    private val checkFavouriteStatus: CheckFavouriteStatusUseCase,
    private val networkLauncher: NetworkLauncherApi
) : BaseViewModel() {

    val checkFavouriteStatusLiveData by LiveDataDelegate<List<MovieUIModel>>()

    val loadingLiveData by LiveDataDelegate<Boolean>()
    val fetchFavouriteMoviesLivedata by LiveDataDelegate<List<MovieUIModel>>()

    private val _fetchMoviesStateFlow = MutableStateFlow<PagingData<MovieUIModel>?>(null)
    val fetchMoviesStateFlow get() = _fetchMoviesStateFlow.asStateFlow()

    private val _categoryStateFlow = MutableStateFlow(CategoryType.POPULAR)
    private val categoryStateFlow = _categoryStateFlow.asStateFlow()

    private val _searchStateFlow = MutableStateFlow<PagingData<MovieUIModel>?>(null)
    val searchStateFlow = _searchStateFlow.asStateFlow()

    private val loader = get<LoaderDialog>(LoaderDialog::class.java)


    init {
//        getMovies()
        startNetworkCall()
    }

    fun startNetworkCall() {
        networkLauncher.startNetwork<Pager<Int, MovieDomainModel>>(scope = viewModelScope, networkStage = {
            loading = {
                d("giorgi", "loader")
                loader.initiateDialog(true)
            }
            execute = {
                val categoryType = categoryStateFlow.value
                moviesUseCase.invoke(categoryType)
            }
            success = {
                d("giorgi", "vm")
            }
            error = {
                loader.initiateDialog(true)
            }
        })
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

    fun checkFavouriteStatus(){
        viewModelScope {
            checkFavouriteStatusLiveData.addValue(moviesUIMapper.mapList(checkFavouriteStatus.invoke()))
        }
    }

    fun navigateToDetails(film: MovieUIModel) {
        navigate(HomeFragmentDirections.actionGlobalDetailsFragment(film))
    }

}