package com.suslanium.gafarov.data.api

import com.suslanium.gafarov.data.Constants
import com.suslanium.gafarov.data.model.MovieDetailsModel
import com.suslanium.gafarov.data.model.MoviesPageResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET(Constants.TOP_LIST_URL)
    suspend fun getTopMoviesPage(
        @Query("page") pageNumber: Int,
        @Query("type") type: String = "TOP_100_POPULAR_FILMS"
    ): MoviesPageResponseModel

    @GET(Constants.DETAILS_URL)
    suspend fun getMovieDetails(
        @Path("id") movieId: Long
    ): MovieDetailsModel

}