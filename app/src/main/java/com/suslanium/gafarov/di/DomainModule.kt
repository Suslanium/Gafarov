package com.suslanium.gafarov.di

import com.suslanium.gafarov.data.api.MovieApiService
import com.suslanium.gafarov.data.converter.MovieDetailsConverter
import com.suslanium.gafarov.data.converter.MovieSummaryConverter
import com.suslanium.gafarov.data.dao.MovieDetailsDao
import com.suslanium.gafarov.data.dao.MovieSummaryDao
import com.suslanium.gafarov.data.repository.FavouriteRepositoryImpl
import com.suslanium.gafarov.data.repository.MovieRepositoryImpl
import com.suslanium.gafarov.domain.repository.FavouritesRepository
import com.suslanium.gafarov.domain.repository.MovieRepository
import com.suslanium.gafarov.domain.usecase.AddFavouriteMovieUseCase
import com.suslanium.gafarov.domain.usecase.GetFavouriteMovieIdsUseCase
import com.suslanium.gafarov.domain.usecase.GetFavouriteMoviesUseCase
import com.suslanium.gafarov.domain.usecase.GetMovieDetailsUseCase
import com.suslanium.gafarov.domain.usecase.GetMoviesListUseCase
import com.suslanium.gafarov.domain.usecase.RemoveFavouriteMovieUseCase
import com.suslanium.gafarov.domain.usecase.SaveMovieDetailsUseCase
import org.koin.dsl.module

private fun provideMovieRepository(
    movieApiService: MovieApiService,
    movieDetailsDao: MovieDetailsDao,
    movieSummaryConverter: MovieSummaryConverter,
    movieDetailsConverter: MovieDetailsConverter
): MovieRepository = MovieRepositoryImpl(
    movieApiService,
    movieDetailsDao,
    movieSummaryConverter,
    movieDetailsConverter
)

private fun provideFavouritesRepository(
    movieDetailsDao: MovieDetailsDao,
    movieSummaryDao: MovieSummaryDao
): FavouritesRepository = FavouriteRepositoryImpl(
    movieDetailsDao,
    movieSummaryDao
)

fun provideDomainModule() = module {
    single {
        provideMovieRepository(get(), get(), get(), get())
    }

    single {
        provideFavouritesRepository(get(), get())
    }

    factory {
        GetMoviesListUseCase(get())
    }

    factory {
        GetMovieDetailsUseCase(get())
    }

    factory {
        AddFavouriteMovieUseCase(get())
    }

    factory {
        GetFavouriteMovieIdsUseCase(get())
    }

    factory {
        GetFavouriteMoviesUseCase(get())
    }

    factory {
        RemoveFavouriteMovieUseCase(get())
    }

    factory {
        SaveMovieDetailsUseCase(get())
    }
}