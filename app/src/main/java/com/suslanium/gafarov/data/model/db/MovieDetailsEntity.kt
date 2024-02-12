package com.suslanium.gafarov.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey
    val id: Long,
    val posterUri: String,
    val title: String,
    val description: String,
    val genres: String,
    val countries: String,
    val year: Int
)
