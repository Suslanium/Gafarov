package com.suslanium.gafarov.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.suslanium.gafarov.data.Constants
import com.suslanium.gafarov.data.api.MovieApiService
import com.suslanium.gafarov.data.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun provideGson(): Gson = GsonBuilder().create()

private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()

private fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl(Constants.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)).build()

private fun provideMovieApi(retrofit: Retrofit): MovieApiService =
    retrofit.create(MovieApiService::class.java)

fun provideNetworkModule() = module {
    factory {
        provideGson()
    }

    factory {
        provideOkHttpClient()
    }

    factory {
        provideRetrofit(get(), get())
    }

    single {
        provideMovieApi(get())
    }
}