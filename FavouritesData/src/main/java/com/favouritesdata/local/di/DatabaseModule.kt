package com.favouritesdata.local.di

import android.content.Context
import androidx.room.Room
import com.favouritesdata.local.database.FavouriteMovieDatabase
import org.koin.dsl.module

private fun provideFavouriteMovieDatabase(context: Context): FavouriteMovieDatabase {
    return Room.databaseBuilder(context, FavouriteMovieDatabase::class.java, "favourite_movie")
        .fallbackToDestructiveMigration().build()
}

private fun provideFavouriteMovieDao(db: FavouriteMovieDatabase) = db.favouriteMovieDao()

val dataBaseModule = module {
    single { provideFavouriteMovieDatabase(get()) }
    single { provideFavouriteMovieDao(get()) }
}