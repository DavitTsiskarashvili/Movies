package com.homedata.remote.di

import com.homedata.mapper.dto.GenresDTOMapper
import com.homedata.mapper.dto.MovieListDTOMapper
import org.koin.dsl.module

val dtoMapperModule = module {
    single { MovieListDTOMapper() }
    single { GenresDTOMapper() }
}