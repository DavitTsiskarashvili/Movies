package com.movies.presentation.model.mapper

import com.movies.common.mapper.Mapper
import com.movies.domain.model.MovieDomainModel
import com.movies.presentation.model.movie.MovieUIModel

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
                isFavourite = isFavourite
            )
        }
}