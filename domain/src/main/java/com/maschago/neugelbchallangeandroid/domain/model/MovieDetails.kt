package com.maschago.neugelbchallangeandroid.domain.model


data class MovieDetails(
    val adult: Boolean?,
    val backdropPath: String?,
    val budget: Int?,
    val genres: List<Genre>?,
    val homepage: String?,
    val id: Int,
    val imdbId: String?,
    val originCountry: List<String>?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val releaseYear: String?,
    val revenue: Int?,
    val spokenLanguage: List<SpokenLanguage>?,
    val title: String?,
    val voteAverage: Float?,
    val voteCount: Int?
)

data class SpokenLanguage(
    val englishName: String,
    val name: String?
)

fun List<SpokenLanguage>?.spokenLanguageAsString(): String = this?.joinToString { it.name ?: it.englishName } ?: "N/A"

data class Genre(
    val id: Int,
    val name: String
)

fun List<Genre>?.genresAsString(): String = this?.joinToString { it.name } ?: "N/A"

fun List<String>?.originCountryAsString(): String = this?.joinToString(", ") ?: "N/A"

