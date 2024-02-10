package com.suslanium.gafarov.domain.entity

data class MovieDetails(
    val posterUri: String,
    val title: String,
    val description: String,
    val genres: List<String>,
    val countries: List<String>,
    val year: Int
)
