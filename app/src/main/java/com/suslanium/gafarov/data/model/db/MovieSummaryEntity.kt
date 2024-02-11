package com.suslanium.gafarov.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_summary")
data class MovieSummaryEntity(
    @PrimaryKey
    val id: Long,
    val thumbnailUri: String,
    val title: String,
    val genre: String,
    val year: Int
)
