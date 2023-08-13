package com.movies.data.remote.service.api

import com.movies.data.remote.dto.details.MovieDetailsDTO
import com.movies.data.remote.dto.genres.GenresDTO
import com.movies.data.remote.dto.movies.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("$PREFIX{category}")
    suspend fun getMovies(@Path("category") category: String, @Query("page") page: Int): Response<MoviesDTO>

    @GET(SEARCH)
    suspend fun searchMovies(@Query("query") query: String, @Query("page") page: Int): Response<MoviesDTO>

    @GET("$PREFIX{movie_id}")
    suspend fun getMoviesDetails(@Path("movie_id") id: Int): Response<MovieDetailsDTO>

    @GET(GENRE)
    suspend fun getMovieGenre(): Response<GenresDTO>

    companion object {
        const val POPULAR = "popular"
        const val TOP_RATED = "top_rated"
        const val UPCOMING = "upcoming"
        const val NOW_PLAYING = "now_playing"

        private const val PREFIX = "3/movie/"
        private const val SEARCH = "3/search/movie"
        private const val GENRE = "3/genre/movie/list"
    }
}