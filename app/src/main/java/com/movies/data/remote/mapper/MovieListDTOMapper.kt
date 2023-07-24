package com.movies.data.remote.mapper

import com.movies.common.mapper.Mapper
import com.movies.data.remote.NetworkConstants.IMAGE_BASE_URL
import com.movies.data.remote.model.MoviesDTO
import com.movies.domain.model.MovieDomainModel
import java.math.BigDecimal
import java.math.RoundingMode

class MovieListDTOMapper : Mapper<MoviesDTO, List<MovieDomainModel>> {
    override fun invoke(model: MoviesDTO): List<MovieDomainModel> =
        with(model) {
            this.results?.map {
                MovieDomainModel(
                    id = it?.id ?: 0,
                    title = it?.title ?: "",
                    rating = formatDoubleToOneDecimal(it?.voteAverage ?: 0.0),
                    poster = IMAGE_BASE_URL + it?.posterPath,
                    releaseDate = it?.releaseDate!!.dropLast(6),
                    overview = it.overview ?: "",
                    isFavourite = false
                )
            } ?: emptyList()
        }

    private fun formatDoubleToOneDecimal(value: Double): Double {
        return BigDecimal(value).setScale(1, RoundingMode.HALF_EVEN).toDouble()
    }

}