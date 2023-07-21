package com.movies.data.remote.service.api

import com.movies.data.remote.model.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET(POPULAR)
    suspend fun getMovies(): Response<MoviesDTO>

    companion object {
        private const val POPULAR = "3/movie/popular"
    }
}


