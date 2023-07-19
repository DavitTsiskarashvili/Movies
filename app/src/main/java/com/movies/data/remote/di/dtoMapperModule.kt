package com.movies.data.remote.di

import com.movies.data.remote.mapper.MovieListDTOMapper
import org.koin.dsl.module

val dtoMapperModule = module {
    single { MovieListDTOMapper() }
}