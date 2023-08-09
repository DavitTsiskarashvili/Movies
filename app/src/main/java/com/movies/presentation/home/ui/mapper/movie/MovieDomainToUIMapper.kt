package com.movies.presentation.home.ui.mapper.movie

import com.movies.common.mapper.Mapper
import com.movies.domain.model.MovieDomainModel
import com.movies.presentation.base.data.model.MovieUIModel

class MovieDomainToUIMapper : Mapper<MovieDomainModel, MovieUIModel> {
    override fun invoke(model: MovieDomainModel): MovieUIModel =
        with(model) {
            MovieUIModel(
                id = id,
                title = title,
                releaseDate = releaseDate,
                rating = getFormattedRating(),
                poster = getFullPoster(),
                overview = overview,
                isFavourite = isFavourite,
                genreString = genreString,
                duration = getFormattedRuntime()
            )
        }

    fun mapList(list: List<MovieDomainModel>): List<MovieUIModel> {
        return list.map {
            invoke(it)
        }
    }
}
