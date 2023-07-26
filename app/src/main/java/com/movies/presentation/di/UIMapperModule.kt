package com.movies.presentation.di

import com.movies.presentation.model.mapper.MovieDomainToUIMapper
import com.movies.presentation.model.mapper.MovieUIToDomainMapper
import org.koin.dsl.module

val uiMapperModule = module {
    single { MovieDomainToUIMapper() }
    single { MovieUIToDomainMapper() }
}