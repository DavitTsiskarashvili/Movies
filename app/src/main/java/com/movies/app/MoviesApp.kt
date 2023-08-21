package com.movies.app

import android.app.Application
import com.commondata.di.networkModule
import com.detailsdata.remote.di.detailsDTOMapperModule
import com.favouritesdata.local.di.dataBaseModule
import com.favouritesdata.local.di.entityMapperModule
import com.homedata.remote.di.dtoMapperModule
import com.homedata.remote.di.retrofitModule
import com.movies.domain.di.repositoryModule
import com.movies.domain.di.useCaseModule
import com.movies.presentation.details.di.detailsViewModelModule
import com.movies.presentation.favourite.di.favouriteViewModelModule
import com.movies.presentation.home.di.homeMapperModule
import com.movies.presentation.home.di.homeViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesApp)
            modules(
                detailsDTOMapperModule,
                retrofitModule,
                networkModule,
                homeViewModelModule,
                homeMapperModule,
                favouriteViewModelModule,
                dtoMapperModule,
                detailsViewModelModule,
                repositoryModule,
                useCaseModule,
                dataBaseModule,
                entityMapperModule,
            )
        }
    }
}