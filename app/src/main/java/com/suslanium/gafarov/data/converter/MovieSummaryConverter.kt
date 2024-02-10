package com.suslanium.gafarov.data.converter

import com.suslanium.gafarov.data.Constants
import com.suslanium.gafarov.data.model.MovieSummaryModel
import com.suslanium.gafarov.domain.entity.MovieSummary
import java.util.Locale

class MovieSummaryConverter(
    private val locale: Locale
) {
    private val localeRu = Locale("ru", "RU")

    fun convert(from: MovieSummaryModel): MovieSummary =
        with(from) {
            MovieSummary(
                id = filmId,
                thumbnailUri = posterUrlPreview ?: posterUrl ?: Constants.EMPTY_STRING,
                title =
                if (locale == localeRu)
                    nameRu ?: nameEn ?: Constants.EMPTY_STRING
                else
                    nameEn ?: nameRu ?: Constants.EMPTY_STRING,
                genre = genres?.firstOrNull()?.genre?.replaceFirstChar {
                    it.titlecase(localeRu)
                } ?: Constants.EMPTY_STRING,
                year = year ?: 0
            )
        }
}