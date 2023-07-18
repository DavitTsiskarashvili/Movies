package com.movies.data.remote.mapper

import com.movies.common.mapper.Mapper
import com.movies.data.remote.model.MoviesDTO
import com.movies.domain.model.MovieDomainModel

class MovieListDTOMapper : Mapper<MoviesDTO, List<MovieDomainModel>> {
    override fun invoke(model: MoviesDTO): List<MovieDomainModel> =
        with(model) {
            this.results?.map {
                MovieDomainModel(
                    id = it?.id,
                    title = it?.title,
                    rating = it?.voteAverage,
                    poster = IMAGE_BASE_URL + it?.posterPath,
                    releaseDate = it?.releaseDate,
                )
            } ?: emptyList()
        }

    companion object {
        const val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w500"
    }
}

