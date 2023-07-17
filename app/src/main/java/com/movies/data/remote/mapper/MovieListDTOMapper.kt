package com.movies.data.remote.mapper

import com.movies.common.mapper.Mapper
import com.movies.data.remote.model.MoviesDTO
import com.movies.domain.model.MovieDomainModel

class MovieListDTOMapper : Mapper<MoviesDTO, List<MovieDomainModel>> {
    override fun invoke(model: MoviesDTO): List<MovieDomainModel> =
        with(model) {
            films.map {
                MovieDomainModel(
                    filmId = it.filmId,
                    filmName = it.filmName,
                    filmTrailer = it.filmTrailer,
                    filmImage = it.images.poster.medium.filmImage,
                    synopsisLong = it.synopsisLong,
                    releaseDate = it.releaseDates[0].releaseDate
                )
            }
        }
}