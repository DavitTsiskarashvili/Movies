package com.movies.data.remote.di

import com.movies.data.remote.NetworkConstants.API_KEY
import com.movies.data.remote.NetworkConstants.BASE_URL
import com.movies.data.remote.network.NetworkLauncher
import com.movies.data.remote.network.NetworkLauncherApi
import com.movies.data.remote.service.api.ServiceApi
import com.movies.presentation.view.loader.LoaderDialog
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun createInterceptor(): Interceptor {
    return Interceptor { chain ->
        val request = chain.request().newBuilder()
            .header("Authorization", API_KEY)
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
    single { LoaderDialog(get()) }
    single<NetworkLauncherApi> { NetworkLauncher(loader = get()) }
}