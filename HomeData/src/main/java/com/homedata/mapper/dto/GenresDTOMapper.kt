
package com.homedata.mapper.dto

import com.commondomain.mapper.Mapper
import com.homedata.remote.dto.genres.GenresDTO
import com.homedomain.model.GenreDomainModel

class GenresDTOMapper : com.commondomain.mapper.Mapper<GenresDTO, List<GenreDomainModel>> {
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