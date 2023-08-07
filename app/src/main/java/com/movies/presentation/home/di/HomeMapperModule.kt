package com.movies.presentation.home.di

import com.movies.presentation.home.ui.mapper.MovieDomainToUIMapper
import com.movies.presentation.home.ui.mapper.MovieUIToDomainMapper
import org.koin.dsl.module

val homeMapperModule = module {
    single { MovieDomainToUIMapper() }
    single { MovieUIToDomainMapper() }
}