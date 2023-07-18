package com.movies.data.remote.di

import com.movies.data.remote.service.api.ServiceApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.themoviedb.org/"

private fun createInterceptor(): Interceptor {
    return Interceptor { chain ->
        val request = chain.request().newBuilder()
            .header("Authorization", ApiKey.VALUE)
            .build()
        chain.proceed(request)
    }
}

private fun creteOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(createInterceptor())
        .build()
}

private fun createRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(creteOkhttpClient())
        .build()
}

val networkModule = module {
    single { createRetrofit() }
    single { get<Retrofit>().create(ServiceApi::class.java) }
}

object ApiKey {
    const val VALUE =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4NmQ2OGQ3ZjRhMGUzZGNjZmE0NWU0ZTk3YjQ1ZjM3OSIsInN1YiI6IjYzMDEwMWQ5N2Q0MWFhMDA3OWJkNzYwMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.eBH951remuFztKssncwnramEF6gdsVu350VI872cuwU"
}