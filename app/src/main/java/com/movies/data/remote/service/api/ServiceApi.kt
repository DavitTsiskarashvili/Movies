package com.movies.data.remote.service.api

import com.movies.data.remote.dto.details.MovieDetailsDTO
import com.movies.data.remote.dto.genres.GenresDTO
import com.movies.data.remote.dto.movies.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("$PREFIX$CATEGORY_PREFIX{category}")
    suspend fun getMovies(@Path("category") category: String, @Query("page") page: Int): Response<MoviesDTO>

    @GET(PREFIX+SEARCH)
    suspend fun searchMovies(@Query("query") query: String, @Query("page") page: Int): Response<MoviesDTO>

    @GET(PREFIX+GENRE)
    suspend fun getMovieGenre(): Response<GenresDTO>

    @GET(PREFIX+DETAILS)
    suspend fun getMoviesDetails(@Path("movie_id") id: Int): Response<MovieDetailsDTO>

    companion object {
        const val PREFIX = "3/"
        const val CATEGORY_PREFIX = "movie/"

        const val SEARCH = "search/movie"

        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"

        const val GENRE = "genre/movie/list"
        const val DETAILS = "movie/{movie_id}"
    }
}