package com.movies.data.remote.service.api

import com.movies.data.remote.model.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {
    @GET("/filmsNowShowing")
    suspend fun getMovies(): Response<List<MoviesDTO>>
}


