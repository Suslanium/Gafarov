package com.suslanium.gafarov.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suslanium.gafarov.data.dao.MovieDetailsDao
import com.suslanium.gafarov.data.dao.MovieSummaryDao
import com.suslanium.gafarov.data.model.db.MovieDetailsEntity
import com.suslanium.gafarov.data.model.db.MovieSummaryEntity

@Database(entities = [MovieDetailsEntity::class, MovieSummaryEntity::class], version = 1)
abstract class MovieDataBase : RoomDatabase() {

    abstract fun movieDetailsDao(): MovieDetailsDao

    abstract fun movieSummaryDao(): MovieSummaryDao

}