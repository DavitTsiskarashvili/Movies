package com.movies.presentation.di

import com.movies.presentation.model.mapper.MovieUIMapper
import org.koin.dsl.module

val uiMapperModule = module {
    single { MovieUIMapper() }
}