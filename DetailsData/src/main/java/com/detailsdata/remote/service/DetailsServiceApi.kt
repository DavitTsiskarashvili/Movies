package com.detailsdata.remote.service

import com.detailsdata.remote.dto.details.MovieDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsServiceApi {
    @GET("$PREFIX{movie_id}")
    suspend fun getMoviesDetails(@Path("movie_id") id: Int): Response<MovieDetailsDTO>

    companion object {
        private const val PREFIX = "3/movie/"
    }
}