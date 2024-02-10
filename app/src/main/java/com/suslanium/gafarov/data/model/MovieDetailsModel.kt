package com.suslanium.gafarov.data.model

import androidx.annotation.Keep

@Keep
data class MovieDetailsModel(
    val kinopoiskId: Long,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val posterUrl: String?,
    val coverUrl: String?,
    val year: Int?,
    val description: String?,
    val countries: List<CountryModel>?,
    val genres: List<GenreModel>?
)
