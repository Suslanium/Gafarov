package com.suslanium.gafarov.data.database

import androidx.room.TypeConverter

class StringListConverter {

    @TypeConverter
    fun fromStringList(strings: List<String>): String {
        return strings.joinToString(",")
    }

    @TypeConverter
    fun toStringList(string: String): List<String> {
        return string.split(",")
    }

}