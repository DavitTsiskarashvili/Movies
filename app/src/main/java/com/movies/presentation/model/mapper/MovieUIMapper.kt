package com.movies.presentation.model.mapper

import com.movies.common.mapper.Mapper
import com.movies.domain.model.MovieDomainModel
import com.movies.presentation.model.movie.MovieUIModel

class MovieUIMapper : Mapper<MovieDomainModel, MovieUIModel> {
    override fun invoke(model: MovieDomainModel): MovieUIModel =
        with(model) {
            MovieUIModel(
                filmId = filmId,
                filmName = filmName,
                filmTrailer = filmTrailer,
                filmImage = filmImage,
                synopsisLong = synopsisLong,
                releaseDate = releaseDate
            )
        }

    fun mapList(list: List<MovieDomainModel>): List<MovieUIModel>{
        return list.map {
            invoke(it)
        }
    }
}
