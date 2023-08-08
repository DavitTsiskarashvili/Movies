package com.movies.data.remote.di

import com.movies.data.mapper.dto.GenresDTOMapper
import com.movies.data.mapper.dto.MovieDetailsDTOMapper
import com.movies.data.mapper.dto.MovieListDTOMapper
import org.koin.dsl.module

val dtoMapperModule = module {
    single { MovieDetailsDTOMapper() }
    single { MovieListDTOMapper() }
    single { GenresDTOMapper() }
}