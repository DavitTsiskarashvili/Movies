package com.movies.presentation.home.ui.mapper.movie

import com.commondata.mapper.Mapper
import com.movies.domain.model.MovieDomainModel
import com.movies.presentation.base.data.model.MovieUIModel

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