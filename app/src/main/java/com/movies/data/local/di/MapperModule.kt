package com.movies.data.local.di

import com.movies.data.mapper.entity.FavouriteMovieDomainToEntityMapper
import com.movies.data.mapper.entity.FavouriteMovieEntityToDomainMapper
import org.koin.dsl.module

val entityMapperModule =  module {
    single { FavouriteMovieEntityToDomainMapper() }
    single { FavouriteMovieDomainToEntityMapper() }
    single {  }
}