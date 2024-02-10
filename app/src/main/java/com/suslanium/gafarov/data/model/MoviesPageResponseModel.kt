package com.suslanium.gafarov.data.model

import androidx.annotation.Keep

@Keep
data class MoviesPageResponseModel(
    val pagesCount: Int,
    val films: List<MovieSummaryModel>
)
