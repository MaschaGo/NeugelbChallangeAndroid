package com.maschago.neugelbchallangeandroid.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterUrl: String?,
    val description: String,
    val releaseDate: String,
)