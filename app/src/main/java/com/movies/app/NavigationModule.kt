package com.movies.app

import com.commonpresentation.navigation.NavControllerManager
import com.example.featurefavouritesimpl.favourite.navigation.FavouritesNavigationImpl
import com.example.featurehomeimpl.navigation.HomeNavigationImpl
import com.featuredetailsapi.navigation.DetailsNavigationApi
import com.featuredetailsimpl.details.navigation.DetailsNavigationImpl
import com.featurefavouritesapi.navigation.FavouritesNavigationApi
import com.homeapi.navigation.HomeNavigationApi
import com.movies.presentation.navigation.NavControllerManagerImpl
import org.koin.dsl.module

val navigationModule = module {
    single<NavControllerManager> { NavControllerManagerImpl() }
    single<HomeNavigationApi> { HomeNavigationImpl(navControllerManager = get()) }
    single<FavouritesNavigationApi> { FavouritesNavigationImpl(navControllerManager = get()) }
    single<DetailsNavigationApi> { DetailsNavigationImpl(navControllerManager = get()) }
}