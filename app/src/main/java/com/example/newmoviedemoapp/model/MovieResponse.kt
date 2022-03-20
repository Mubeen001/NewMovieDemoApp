package com.example.newmoviedemoapp.model

import androidx.annotation.Keep

@Keep
data class MovieResponse(
    val dates: Dates,
    val page: Int,
    val results: List<moviesResult>,
    val total_pages: Int,
    val total_results: Int
)