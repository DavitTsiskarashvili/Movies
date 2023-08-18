package com.movies.app

import android.app.Application
import com.movies.data.local.di.dataBaseModule
import com.movies.data.local.di.entityMapperModule
import com.movies.data.remote.di.dtoMapperModule
import com.movies.data.remote.di.networkModule
import com.movies.domain.di.repositoryModule
import com.movies.domain.di.useCaseModule
import com.movies.presentation.details.di.detailsModule
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
                networkModule,
                homeViewModelModule,
                homeMapperModule,
                dtoMapperModule,
                detailsModule,
                repositoryModule,
                useCaseModule,
                dataBaseModule,
                entityMapperModule
            )
        }
    }
}