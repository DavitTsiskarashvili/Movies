package com.movies.data.mapper.dto

import com.movies.common.mapper.Mapper
import com.movies.data.remote.dto.details.MovieDetailsDTO
import com.movies.domain.model.MovieDomainModel

class MovieDetailsDTOMapper : Mapper<MovieDetailsDTO, MovieDomainModel> {
    override fun invoke(model: MovieDetailsDTO): MovieDomainModel =
        with(model) {
            MovieDomainModel(
                id = id ?: 0,
                title = title ?: "",
                releaseDate = releaseDate!!.dropLast(6),
                overview = overview ?: "",
                poster = posterPath ?: "",
                rating = voteAverage ?: 0.0,
                runtime = runtime,
                isFavourite = false,
                genreString = genres?.getOrNull(0)?.name ?: ""
            )
        }
}