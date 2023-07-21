package com.movies.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.movies.data.local.entity.FavouriteMovieEntity

@Dao
interface FavouriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteMovie(favouriteMovieEntity: FavouriteMovieEntity)

    @Delete
    suspend fun deleteFavouriteMovie(favouriteMovieEntity: FavouriteMovieEntity)

    @Query("SELECT * FROM favourite_movie")
    suspend fun getFavouriteMovies(): List<FavouriteMovieEntity>
}