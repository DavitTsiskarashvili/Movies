package com.favouritesdata.mapper

import com.commondomain.mapper.Mapper
import com.commondomain.model.MovieDomainModel
import com.favouritesdata.local.entity.FavouriteMovieEntity

class FavouriteMovieDomainToEntityMapper : Mapper<MovieDomainModel, FavouriteMovieEntity> {
    override fun invoke(model: MovieDomainModel): FavouriteMovieEntity =
        with(model) {
            FavouriteMovieEntity(
                id = id,
                title = title,
                rating = getFormattedRating(),
                releaseDate = releaseDate,
                poster = getFullPoster(),
                overview = overview,
                genre = genreString!!,
            )
        }
}