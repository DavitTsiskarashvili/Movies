package com.movies.data.remote.service.di

import com.movies.data.remote.service.api.ServiceApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api-gate2.movieglu.com"

private fun createRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

val networkModule = module {
    single { createRetrofit() }
    single { get<Retrofit>().create(ServiceApi::class.java) }
}