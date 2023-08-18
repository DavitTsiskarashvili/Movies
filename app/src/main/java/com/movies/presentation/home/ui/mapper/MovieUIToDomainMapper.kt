package com.movies.presentation.home.ui.mapper

import com.movies.common.mapper.Mapper
import com.movies.domain.model.MovieDomainModel
import com.movies.presentation.base.data.MovieUIModel

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