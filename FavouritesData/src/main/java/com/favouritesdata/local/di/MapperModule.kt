package com.favouritesdata.local.di

import com.favouritesdata.mapper.FavouriteMovieDomainToEntityMapper
import com.favouritesdata.mapper.FavouriteMovieEntityToDomainMapper
import org.koin.dsl.module

val entityMapperModule =  module {
    single { FavouriteMovieEntityToDomainMapper() }
    single { FavouriteMovieDomainToEntityMapper() }
}