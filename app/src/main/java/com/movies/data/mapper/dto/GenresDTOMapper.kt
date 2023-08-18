package com.movies.data.mapper.dto

import com.movies.common.mapper.Mapper
import com.movies.data.remote.dto.genres.GenresDTO
import com.movies.domain.model.GenreDomainModel

class GenresDTOMapper : Mapper<GenresDTO, List<GenreDomainModel>> {
    override fun invoke(model: GenresDTO): List<GenreDomainModel> =
        with(model) {
            this.genres.map {
                GenreDomainModel(
                    id = it.id,
                    name = it.name
                )
            }
        }
}