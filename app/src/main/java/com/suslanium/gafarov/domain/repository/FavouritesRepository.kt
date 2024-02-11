package com.suslanium.gafarov.domain.repository

import com.suslanium.gafarov.domain.entity.MovieDetails
import com.suslanium.gafarov.domain.entity.MovieSummary
import kotlinx.coroutines.flow.Flow

interface FavouritesRepository {

    fun getFavoriteMovies(): Flow<List<MovieSummary>>

    fun getFavoriteMovieIds(): Flow<HashSet<Long>>

    suspend fun addFavoriteMovie(summary: MovieSummary, details: MovieDetails)

    suspend fun removeFavoriteMovie(movieId: Long)

}