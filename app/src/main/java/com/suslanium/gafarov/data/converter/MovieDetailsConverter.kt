package com.suslanium.gafarov.data.converter

import androidx.compose.ui.util.fastJoinToString
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
                //This is incorrect because the locale may change over time, but I realized this a bit too late
                genres = (genres?.map { genreModel -> genreModel.genre }
                    ?: emptyList()).fastJoinToString(", "),
                countries = (countries?.map { countryModel -> countryModel.country }
                    ?: emptyList()).fastJoinToString(", "),
                year = year ?: 0
            )
        }
}