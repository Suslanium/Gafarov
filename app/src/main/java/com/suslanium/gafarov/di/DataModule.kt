package com.suslanium.gafarov.di

import android.content.Context
import androidx.room.Room
import com.suslanium.gafarov.data.converter.MovieDetailsConverter
import com.suslanium.gafarov.data.converter.MovieSummaryConverter
import com.suslanium.gafarov.data.dao.MovieDetailsDao
import com.suslanium.gafarov.data.dao.MovieSummaryDao
import com.suslanium.gafarov.data.database.MovieDataBase
import org.koin.android.ext.koin.androidApplication
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

    single {
        Room.databaseBuilder(
            androidApplication(),
            MovieDataBase::class.java,
            "movie_db"
        ).build()
    }

    single<MovieDetailsDao> {
        val db = get<MovieDataBase>()
        db.movieDetailsDao()
    }

    single<MovieSummaryDao> {
        val db = get<MovieDataBase>()
        db.movieSummaryDao()
    }
}