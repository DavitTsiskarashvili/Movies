package com.homedata.remote.di

import com.homedata.remote.service.ServiceApi
import org.koin.dsl.module
import retrofit2.Retrofit

val retrofitModule = module {
    single { get<Retrofit>().create(ServiceApi::class.java) }
}