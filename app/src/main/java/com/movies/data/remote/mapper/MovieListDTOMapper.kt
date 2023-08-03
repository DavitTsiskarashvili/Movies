package com.movies.data.remote.mapper

import com.movies.common.mapper.Mapper
import com.movies.data.remote.dto.movies.MoviesDTO
import com.movies.domain.model.MovieDomainModel

class MovieListDTOMapper : Mapper<MoviesDTO, List<MovieDomainModel>> {
    override fun invoke(model: MoviesDTO): List<MovieDomainModel> =
        with(model) {
            this.results?.map {
                MovieDomainModel(
                    id = it?.id ?: 0,
                    title = it?.title ?: "",
                    rating = it?.voteAverage ?: 0.0,
                    poster = it?.posterPath ?: "",
                    releaseDate = it?.releaseDate!!.dropLast(6),
                    overview = it.overview ?: "",
                    isFavourite = false
                )
            } ?: emptyList()
        }

}