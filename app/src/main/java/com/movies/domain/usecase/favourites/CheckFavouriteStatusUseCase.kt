package com.movies.domain.usecase.favourites

import com.movies.domain.model.MovieDomainModel
import com.movies.domain.repository.FavouriteMovieRepository
import com.movies.domain.usecase.base.BaseUseCase

//class CheckFavouriteStatusUseCase (
//    private val favouriteMovieRepository: FavouriteMovieRepository
//) : BaseUseCase<Unit, List<MovieDomainModel>>() {
//
//    override suspend fun invoke(params: Unit?): List<MovieDomainModel> {
//        return favouriteMovieRepository.isFavouriteMovie()
//    }
//}