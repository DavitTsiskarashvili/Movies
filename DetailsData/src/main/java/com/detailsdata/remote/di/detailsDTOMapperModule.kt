package com.detailsdata.remote.di

import com.detailsdata.mapper.dto.MovieDetailsDTOMapper
import org.koin.dsl.module

val detailsDTOMapperModule = module {
    single { MovieDetailsDTOMapper() }
}