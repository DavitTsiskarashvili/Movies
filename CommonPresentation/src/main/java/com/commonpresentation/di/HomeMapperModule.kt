package com.example.featurehomeimpl.home.di

import com.commonpresentation.mapper.movie.MovieDomainToUIMapper
import com.commonpresentation.mapper.movie.MovieUIToDomainMapper
import org.koin.dsl.module

val homeMapperModule = module {
    single { MovieDomainToUIMapper() }
    single { MovieUIToDomainMapper() }
}