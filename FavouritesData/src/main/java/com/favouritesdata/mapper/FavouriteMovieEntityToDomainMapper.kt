package com.favouritesdata.mapper

import com.commondata.mapper.Mapper
import com.commondomain.model.MovieDomainModel
import com.favouritesdata.local.entity.FavouriteMovieEntity

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
                isFavourite = true,
                genreString = genre
            )
        }
}