package com.suslanium.gafarov.data.model

import androidx.annotation.Keep

@Keep
data class MovieSummaryModel(
    val filmId: Long,
    val nameRu: String?,
    val nameEn: String?,
    val countries: List<CountryModel>?,
    val genres: List<GenreModel>?,
    val rating: String?,
    val year: Int?,
    val posterUrl: String?,
    val posterUrlPreview: String?
)
