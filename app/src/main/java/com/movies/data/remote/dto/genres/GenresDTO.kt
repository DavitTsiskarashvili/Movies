package com.movies.data.remote.dto.genres

data class GenresDTO(
    val genres: List<Genre>
) {
    data class Genre(
        val id: Int,
        val name: String
    )
}
