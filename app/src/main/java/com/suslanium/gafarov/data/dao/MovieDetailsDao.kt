package com.suslanium.gafarov.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.suslanium.gafarov.data.model.db.MovieDetailsEntity

@Dao
interface MovieDetailsDao {

    @Query("SELECT * FROM movie_details WHERE id = :id")
    suspend fun getMovieDetailsById(id: Long): MovieDetailsEntity?

    @Upsert
    suspend fun upsertMovieDetails(detailsEntity: MovieDetailsEntity)

    @Query("DELETE FROM movie_details WHERE id = :id")
    suspend fun deleteMovieDetailsById(id: Long)

}