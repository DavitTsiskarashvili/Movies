package com.favouritesdata.mapper

import com.commondata.mapper.Mapper
import com.favouritesdata.local.entity.FavouriteMovieEntity
import com.movies.domain.model.MovieDomainModel

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