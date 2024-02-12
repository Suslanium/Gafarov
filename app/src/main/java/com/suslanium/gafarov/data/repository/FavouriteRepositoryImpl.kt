package com.suslanium.gafarov.data.repository

import com.suslanium.gafarov.data.converter.DatabaseMovieConverter
import com.suslanium.gafarov.data.dao.MovieDetailsDao
import com.suslanium.gafarov.data.dao.MovieSummaryDao
import com.suslanium.gafarov.domain.entity.MovieDetails
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.domain.repository.FavouritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FavouriteRepositoryImpl(
    private val detailsDao: MovieDetailsDao, private val summaryDao: MovieSummaryDao
) : FavouritesRepository {

    override fun getFavoriteMovies(): Flow<List<MovieSummary>> = summaryDao.getCachedMovies().map {
        it.map { movieSummaryEntity ->
            DatabaseMovieConverter.convertEntityToSummary(movieSummaryEntity)
        }
    }.flowOn(Dispatchers.IO)

    override fun getFavoriteMovieIds(): Flow<HashSet<Long>> =
        summaryDao.getCachedMovieIds().map { it.toHashSet() }.flowOn(Dispatchers.IO)

    override suspend fun addFavoriteMovie(summary: MovieSummary) {
        summaryDao.upsertMovieSummary(DatabaseMovieConverter.convertSummaryToEntity(summary))
    }

    override suspend fun saveMovieDetails(movieId: Long, movieDetails: MovieDetails) {
        detailsDao.upsertMovieDetails(
            DatabaseMovieConverter.convertDetailsToEntity(
                movieId, movieDetails
            )
        )
    }

    override suspend fun removeFavoriteMovie(movieId: Long) {
        summaryDao.deleteMovieSummaryById(movieId)
    }

}