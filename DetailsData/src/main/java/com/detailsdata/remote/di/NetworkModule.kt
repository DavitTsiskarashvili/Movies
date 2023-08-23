package com.detailsdata.remote.di

import com.detailsdata.remote.service.DetailsServiceApi
import org.koin.dsl.module
import retrofit2.Retrofit

val detailsRetrofitModule = module {
    single { get<Retrofit>().create(DetailsServiceApi::class.java) }
}