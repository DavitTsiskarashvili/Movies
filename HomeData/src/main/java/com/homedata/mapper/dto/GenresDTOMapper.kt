
package com.homedata.mapper.dto

import com.commondata.mapper.Mapper
import com.homedata.remote.dto.genres.GenresDTO
import com.movies.domain.model.GenreDomainModel

class GenresDTOMapper : Mapper<GenresDTO, List<GenreDomainModel>> {
    override fun invoke(model: GenresDTO): List<GenreDomainModel> =
        with(model) {
            genres.map {
                GenreDomainModel(
                    id = it.id,
                    name = it.name
                )
            }
        }
}