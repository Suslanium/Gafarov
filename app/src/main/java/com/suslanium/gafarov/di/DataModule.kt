package com.suslanium.gafarov.di

import android.content.Context
import com.suslanium.gafarov.data.converter.MovieDetailsConverter
import com.suslanium.gafarov.data.converter.MovieSummaryConverter
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private fun provideMovieSummaryConverter(context: Context): MovieSummaryConverter =
    MovieSummaryConverter(context.resources.configuration.locales.get(0))

private fun provideMovieDetailsConverter(context: Context): MovieDetailsConverter =
    MovieDetailsConverter(context.resources.configuration.locales.get(0))

fun provideDataModule() = module {
    factory {
        provideMovieSummaryConverter(androidContext())
    }

    factory {
        provideMovieDetailsConverter(androidContext())
    }
}