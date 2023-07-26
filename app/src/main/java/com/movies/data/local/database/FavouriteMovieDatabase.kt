package com.movies.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.movies.data.local.dao.FavouriteMoviesDao
import com.movies.data.local.entity.FavouriteMovieEntity

@Database(entities = [FavouriteMovieEntity::class], version = 1)
abstract class FavouriteMovieDatabase : RoomDatabase() {
    abstract fun favouriteMovieDao(): FavouriteMoviesDao
}