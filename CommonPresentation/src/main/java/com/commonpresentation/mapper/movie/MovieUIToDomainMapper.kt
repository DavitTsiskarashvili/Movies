package com.commonpresentation.mapper.movie

import com.commondomain.mapper.Mapper
import com.commondomain.model.MovieDomainModel
import com.commonpresentation.presentation.base.data.model.MovieUIModel

class MovieUIToDomainMapper: Mapper<MovieUIModel, MovieDomainModel> {
    override fun invoke(model: MovieUIModel): MovieDomainModel =
        with(model) {
            MovieDomainModel(
                id = id,
                title = title,
                releaseDate = releaseDate,
                rating = rating,
                poster = poster,
                overview = overview,
                isFavourite = isFavourite,
                genreString = genreString
            )
        }
}