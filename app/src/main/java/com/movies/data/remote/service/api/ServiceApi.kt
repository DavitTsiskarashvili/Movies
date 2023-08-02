package com.movies.data.remote.service.api

import com.movies.data.remote.model.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("{category}")
    suspend fun getMovies(@Path("category") category: String, @Query("page") page: Int): Response<MoviesDTO>

    @GET(SEARCH)
    suspend fun searchMovies(@Query("query") query: String, @Query("page") page: Int): Response<MoviesDTO>

    companion object {
        const val SEARCH = "3/search/movie"
         const val POPULAR = "3/movie/popular"
         const val TOP_RATED = "3/movie/top_rated"
    }
}