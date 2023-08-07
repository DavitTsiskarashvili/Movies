package com.movies.domain.usecase.favourites

import com.movies.domain.repository.FavouriteMovieRepository
import com.movies.domain.usecase.base.BaseUseCase

class CheckFavouriteStatusUseCase (
    private val favouriteMovieRepository: FavouriteMovieRepository
) : BaseUseCase<Int, Boolean>() {

    override suspend fun invoke(params: Int?): Boolean {
        return favouriteMovieRepository.isFavouriteMovie(params!!)
    }
}