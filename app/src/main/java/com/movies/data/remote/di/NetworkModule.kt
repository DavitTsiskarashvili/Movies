package com.movies.data.remote.di

import com.movies.common.extensions.LocalDateTimeProvider
import com.movies.data.remote.service.api.ServiceApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api-gate2.movieglu.com"

private fun createInterceptor(): Interceptor {
    return Interceptor { chain ->
        val request = chain.request().newBuilder()
            .header("client", "SPAC_0")
            .header("x-api-key", "TtqWcYf8hO6nKghu94WFb3rTWiefvOnT78qeHEx0")
            .header("authorization", "Basic U1BBQ18wOnJUaTZkM3NtejJCcg==")
            .header("territory", "DE")
            .header("api-version", "v200")
            .header("device-datetime", LocalDateTimeProvider.dateProvider())
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