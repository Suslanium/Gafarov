package com.suslanium.gafarov.data.converter

import com.suslanium.gafarov.data.Constants
import com.suslanium.gafarov.data.model.MovieDetailsModel
import com.suslanium.gafarov.domain.entity.MovieDetails
import java.util.Locale

class MovieDetailsConverter(
    private val locale: Locale
) {
    private val localeRu = Locale("ru", "RU")

    fun convert(from: MovieDetailsModel): MovieDetails =
        with(from) {
            MovieDetails(
                posterUri = posterUrl ?: coverUrl ?: Constants.EMPTY_STRING,
                title =
                if (locale == localeRu)
                    nameRu ?: nameOriginal ?: nameEn ?: Constants.EMPTY_STRING
                else
                    nameOriginal ?: nameEn ?: nameRu ?: Constants.EMPTY_STRING,
                description = description ?: Constants.EMPTY_STRING,
                genres = genres?.map { genreModel -> genreModel.genre } ?: emptyList(),
                countries = countries?.map { countryModel -> countryModel.country } ?: emptyList(),
                year = year ?: 0
            )
        }
}