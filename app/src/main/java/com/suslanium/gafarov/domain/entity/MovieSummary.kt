package com.suslanium.gafarov.domain.entity

import java.util.UUID

data class MovieSummary(
    val listUUID: UUID = UUID.randomUUID(),
    val id: Long,
    val thumbnailUri: String,
    val title: String,
    val genre: String,
    val year: Int
)