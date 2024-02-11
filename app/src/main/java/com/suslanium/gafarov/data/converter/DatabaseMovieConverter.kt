package com.suslanium.gafarov.data.converter

import com.suslanium.gafarov.data.model.db.MovieDetailsEntity
import com.suslanium.gafarov.data.model.db.MovieSummaryEntity
import com.suslanium.gafarov.domain.entity.MovieDetails
import com.suslanium.gafarov.domain.entity.MovieSummary

object DatabaseMovieConverter {

    fun convertSummaryToEntity(from: MovieSummary): MovieSummaryEntity = with(from) {
        MovieSummaryEntity(
            id, thumbnailUri, title, genre, year
        )
    }

    fun convertDetailsToEntity(id: Long, from: MovieDetails): MovieDetailsEntity = with(from) {
        MovieDetailsEntity(
            id, posterUri, title, description, genres, countries, year
        )
    }

    fun convertEntityToSummary(from: MovieSummaryEntity): MovieSummary = with(from) {
        MovieSummary(id, thumbnailUri, title, genre, year)
    }

    fun convertEntityToDetails(from: MovieDetailsEntity): MovieDetails = with(from) {
        MovieDetails(posterUri, title, description, genres, countries, year)
    }

}