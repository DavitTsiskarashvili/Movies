package com.movies

import android.app.Application
import com.movies.data.local.module.dataBaseModule
import com.movies.data.mapper.module.entityMapperModule
import com.movies.data.remote.di.dtoMapperModule
import com.movies.data.remote.di.networkModule
import com.movies.domain.di.repositoryModule
import com.movies.domain.di.useCaseModule
import com.movies.presentation.di.uiMapperModule
import com.movies.presentation.di.viewModelModule
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
                viewModelModule,
                dtoMapperModule,
                uiMapperModule,
                repositoryModule,
                useCaseModule,
                dataBaseModule,
                entityMapperModule
            )
        }
    }
}