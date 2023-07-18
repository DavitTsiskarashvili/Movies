package com.movies.data.remote.service.api

import com.movies.data.remote.model.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET("3/movie/popular")
    suspend fun getMovies(): Response<MoviesDTO>
}


