package com.suslanium.gafarov.data

object Constants {
    const val AUTH_HEADER = "X-API-KEY"
    const val API_KEY = "e30ffed0-76ab-4dd6-b41f-4c9da2b2735b"
    const val BASE_URL = "https://kinopoiskapiunofficial.tech"
    const val TOP_LIST_URL = "/api/v2.2/films/top"
    //The /api/v2.2/films/top/{id} endpoint either doesn't exist or doesn't work
    const val DETAILS_URL = "/api/v2.2/films/{id}"
    const val EMPTY_STRING = ""
}