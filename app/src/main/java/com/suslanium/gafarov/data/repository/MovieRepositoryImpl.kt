package com.suslanium.gafarov.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.suslanium.gafarov.data.api.MovieApiService
import com.suslanium.gafarov.data.converter.MovieDetailsConverter
import com.suslanium.gafarov.data.converter.MovieSummaryConverter
import com.suslanium.gafarov.data.paging.MoviesPagingSource
import com.suslanium.gafarov.domain.entity.MovieDetails
import com.suslanium.gafarov.domain.entity.MovieSummary
import com.suslanium.gafarov.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieApiService: MovieApiService,
    private val movieSummaryConverter: MovieSummaryConverter,
    private val movieDetailsConverter: MovieDetailsConverter
) : MovieRepository {

    override fun getMoviesList(): Flow<PagingData<MovieSummary>> {
        return Pager(
            config = PagingConfig(pageSize = 10), initialKey = 1, pagingSourceFactory = {
                MoviesPagingSource(movieApiService, movieSummaryConverter)
            }
        ).flow
    }

    override suspend fun getMovieDetails(movieId: Long): MovieDetails =
        movieDetailsConverter.convert(movieApiService.getMovieDetails(movieId))
}