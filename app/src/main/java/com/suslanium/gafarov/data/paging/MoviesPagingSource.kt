package com.suslanium.gafarov.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.suslanium.gafarov.data.api.MovieApiService
import com.suslanium.gafarov.data.converter.MovieSummaryConverter
import com.suslanium.gafarov.domain.entity.MovieSummary

class MoviesPagingSource(
    private val movieApiService: MovieApiService,
    private val movieSummaryConverter: MovieSummaryConverter
) : PagingSource<Int, MovieSummary>() {

    override fun getRefreshKey(state: PagingState<Int, MovieSummary>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSummary> {
        return try {
            val currentPage = params.key ?: 1
            val moviesPage = movieApiService.getTopMoviesPage(currentPage)
            val movies = moviesPage.films.map { movieSummaryModel ->
                movieSummaryConverter.convert(movieSummaryModel)
            }
            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey =
                if (moviesPage.films.isEmpty() || currentPage == moviesPage.pagesCount)
                    null
                else
                    currentPage + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}