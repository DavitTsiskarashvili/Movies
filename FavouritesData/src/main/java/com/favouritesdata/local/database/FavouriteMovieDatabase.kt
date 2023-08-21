package com.favouritesdata.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.favouritesdata.local.dao.FavouriteMoviesDao
import com.favouritesdata.local.entity.FavouriteMovieEntity

@Database(entities = [FavouriteMovieEntity::class], version = 2)
abstract class FavouriteMovieDatabase : RoomDatabase() {
    abstract fun favouriteMovieDao(): FavouriteMoviesDao
}