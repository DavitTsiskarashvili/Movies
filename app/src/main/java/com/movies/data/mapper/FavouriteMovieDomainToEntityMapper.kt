package com.movies.data.mapper

import com.movies.common.mapper.Mapper
import com.movies.data.local.entity.FavouriteMovieEntity
import com.movies.domain.model.MovieDomainModel

class FavouriteMovieDomainToEntityMapper : Mapper<MovieDomainModel, FavouriteMovieEntity> {
    override fun invoke(model: MovieDomainModel): FavouriteMovieEntity =
        with(model) {
            FavouriteMovieEntity(
                id = id,
                title = title,
                rating = rating,
                releaseDate = releaseDate,
                poster = poster,
                overview = overview
            )
        }
}