package com.suslanium.gafarov.domain.repository

import androidx.paging.PagingData
import com.suslanium.gafarov.domain.entity.MovieDetails
import com.suslanium.gafarov.domain.entity.MovieSummary
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesList(): Flow<PagingData<MovieSummary>>

    suspend fun getMovieDetails(movieId: Long): MovieDetails

}