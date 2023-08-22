package com.movies.app

import android.app.Application
import com.commondata.di.networkModule
import com.commonpresentation.di.homeMapperModule
import com.detailsdata.di.detailsRepositoryModule
import com.detailsdata.remote.di.detailsDTOMapperModule
import com.detailsdomain.di.detailsUseCaseModule
import com.example.featurefavouritesimpl.favourite.di.favouriteViewModelModule
import com.example.featurehomeimpl.home.di.homeViewModelModule
import com.favouritesdata.di.favouritesRepositoryModule
import com.favouritesdata.local.di.dataBaseModule
import com.favouritesdata.local.di.entityMapperModule
import com.favouritesdomain.di.favouritesUseCaseModule
import com.featuredetailsimpl.details.di.detailsViewModelModule
import com.homedata.di.homeRepositoryModule
import com.homedata.remote.di.dtoMapperModule
import com.homedata.remote.di.retrofitModule
import com.homedomain.di.homeUseCaseModule
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
                navigationModule,
                retrofitModule,
                networkModule,
                dataBaseModule,
                dtoMapperModule,
                entityMapperModule,
                detailsDTOMapperModule,
                detailsViewModelModule,
                detailsRepositoryModule,
                detailsUseCaseModule,
                homeMapperModule,
                homeRepositoryModule,
                homeUseCaseModule,
                homeViewModelModule,
                favouritesRepositoryModule,
                favouritesUseCaseModule,
                favouriteViewModelModule
            )
        }
    }
}