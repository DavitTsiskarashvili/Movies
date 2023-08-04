package com.movies.presentation.home.ui.mapper

import com.movies.common.mapper.Mapper
import com.movies.domain.model.MovieDomainModel
import com.movies.presentation.base.data.MovieUIModel

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
                isFavourite = isFavourite
            )
        }

    fun mapList(list: List<MovieDomainModel>): List<MovieUIModel> {
        return list.map {
            invoke(it)
        }
    }
}
