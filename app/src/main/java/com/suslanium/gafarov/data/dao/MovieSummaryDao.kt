package com.suslanium.gafarov.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.suslanium.gafarov.data.model.db.MovieSummaryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieSummaryDao {

    @Query("SELECT id FROM movie_summary")
    fun getCachedMovieIds(): Flow<List<Long>>

    @Query("SELECT * FROM movie_summary")
    fun getCachedMovies(): Flow<List<MovieSummaryEntity>>

    @Upsert
    suspend fun upsertMovieSummary(movieSummaryEntity: MovieSummaryEntity)

    @Query("DELETE FROM movie_summary WHERE id = :id")
    suspend fun deleteMovieSummaryById(id: Long)

}