package com.suslanium.gafarov.di

import com.suslanium.gafarov.data.api.MovieApiService
import com.suslanium.gafarov.data.converter.MovieDetailsConverter
import com.suslanium.gafarov.data.converter.MovieSummaryConverter
import com.suslanium.gafarov.data.repository.MovieRepositoryImpl
import com.suslanium.gafarov.domain.repository.MovieRepository
import com.suslanium.gafarov.domain.usecase.GetMovieDetailsUseCase
import com.suslanium.gafarov.domain.usecase.GetMoviesListUseCase
import org.koin.dsl.module

private fun provideMovieRepository(
    movieApiService: MovieApiService,
    movieSummaryConverter: MovieSummaryConverter,
    movieDetailsConverter: MovieDetailsConverter
): MovieRepository = MovieRepositoryImpl(movieApiService, movieSummaryConverter, movieDetailsConverter)

fun provideDomainModule() = module {
    single {
        provideMovieRepository(get(), get(), get())
    }

    factory {
        GetMoviesListUseCase(get())
    }

    factory {
        GetMovieDetailsUseCase(get())
    }
}