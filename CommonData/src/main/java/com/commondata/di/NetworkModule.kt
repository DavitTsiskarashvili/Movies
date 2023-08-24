package com.commondata.di

import com.commondata.NetworkConstants.BASE_URL
import com.commondata.network.NetworkLauncherImpl
import com.commondomain.network.NetworkLauncher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun createInterceptor(apiKey: String): Interceptor {
    return Interceptor { chain ->
        val request = chain.request().newBuilder()
            .header("Authorization", apiKey)
            .build()
        chain.proceed(request)
    }
}

private fun creteOkhttpClient(apiKey: String): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(createInterceptor(apiKey))
        .build()
}

private fun createRetrofit(apiKey: String): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(creteOkhttpClient(apiKey))
        .build()
}

fun networkModule(apiKey: String) = module {
    single { createRetrofit(apiKey) }
    single<NetworkLauncher> { NetworkLauncherImpl() }
}