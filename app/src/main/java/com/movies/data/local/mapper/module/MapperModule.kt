package com.movies.data.local.mapper.module

import com.movies.data.local.mapper.entity.FavouriteMovieDomainToEntityMapper
import com.movies.data.local.mapper.entity.FavouriteMovieEntityToDomainMapper
import org.koin.dsl.module

val entityMapperModule =  module {
    single { FavouriteMovieEntityToDomainMapper() }
    single { FavouriteMovieDomainToEntityMapper() }
}