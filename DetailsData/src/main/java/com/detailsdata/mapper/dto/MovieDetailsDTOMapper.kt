package com.detailsdata.mapper.dto

import com.commondata.mapper.Mapper
import com.detailsdata.remote.dto.details.MovieDetailsDTO
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
                duration = runtime,
                isFavourite = false,
                genreString = genres?.getOrNull(0)?.name ?: ""
            )
        }
}