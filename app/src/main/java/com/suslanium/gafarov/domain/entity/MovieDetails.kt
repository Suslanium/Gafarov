package com.suslanium.gafarov.domain.entity

data class MovieDetails(
    val posterUri: String,
    val title: String,
    val description: String,
    val genres: String,
    val countries: String,
    val year: Int
)
