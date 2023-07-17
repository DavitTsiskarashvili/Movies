package com.movies.presentation.di

import com.movies.presentation.model.mapper.MovieUIMapper
import org.koin.dsl.module

val mapperUIModule = module {
    single { MovieUIMapper() }
}