package com.movies.data.mapper.module

import com.movies.data.mapper.entity.FavouriteMovieDomainToEntityMapper
import com.movies.data.mapper.entity.FavouriteMovieEntityToDomainMapper
import org.koin.dsl.module

val entityMapperModule =  module {
    single { FavouriteMovieEntityToDomainMapper() }
    single { FavouriteMovieDomainToEntityMapper() }
}