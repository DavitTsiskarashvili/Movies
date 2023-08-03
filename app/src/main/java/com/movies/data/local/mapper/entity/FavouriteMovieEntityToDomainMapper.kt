package com.movies.data.local.mapper.entity

import com.movies.common.mapper.Mapper
import com.movies.data.local.entity.FavouriteMovieEntity
import com.movies.domain.model.MovieDomainModel

class FavouriteMovieEntityToDomainMapper : Mapper<FavouriteMovieEntity, MovieDomainModel> {
    override fun invoke(model: FavouriteMovieEntity): MovieDomainModel =
        with(model) {
            MovieDomainModel(
                id = id,
                title = title,
                releaseDate = releaseDate,
                poster = poster,
                overview = overview,
                rating = rating,
                isFavourite = true
            )
        }
}