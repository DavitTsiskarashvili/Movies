package com.commonpresentation.mapper.movie

import com.commondomain.mapper.Mapper
import com.commondomain.model.MovieDomainModel
import com.commonpresentation.presentation.base.data.model.MovieUIModel

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
