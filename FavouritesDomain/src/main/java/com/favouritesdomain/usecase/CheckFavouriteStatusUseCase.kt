package com.favouritesdomain.usecase

import com.commondomain.base.BaseUseCase
import com.favouritesdomain.repository.FavouriteMovieRepository

class CheckFavouriteStatusUseCase (
    private val favouriteMovieRepository: FavouriteMovieRepository
) : BaseUseCase<Int, Boolean>() {

    override suspend fun invoke(params: Int?): Boolean {
        return favouriteMovieRepository.isFavouriteMovie(params!!)
    }
}