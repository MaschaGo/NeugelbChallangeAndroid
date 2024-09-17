package com.maschago.neugelbchallangeandroid.data.model

import com.google.gson.annotations.SerializedName
import com.maschago.neugelbchallangeandroid.core.extensions.extractYearFromDate
import com.maschago.neugelbchallangeandroid.core.extensions.toDayMonthYearDateFormat
import com.maschago.neugelbchallangeandroid.domain.model.Genre
import com.maschago.neugelbchallangeandroid.domain.model.MovieDetails
import com.maschago.neugelbchallangeandroid.domain.model.SpokenLanguage


data class MovieDetailsDto(
    @SerializedName("adult") val isAdult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val genre: List<GenreResponse>?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("spoken_languages") val spokenLanguageResponses: List<SpokenLanguageResponse>,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("vote_count") val voteCount: Int,
)


fun MovieDetailsDto.toMovieDetails() = MovieDetails(
    isAdult,     backdropPath,    budget,    genre?.map { it.toMovieDetails() },
    homepage,    id,    imdbId,    originCountry,    originalLanguage,
    originalTitle,    overview,    popularity,    posterPath,    releaseDate?.toDayMonthYearDateFormat(),
    releaseDate?.extractYearFromDate(),    revenue,    spokenLanguageResponses.map { it.toMovieDetails() },
    title,    voteAverage,    voteCount
)

data class SpokenLanguageResponse(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("name") val name: String?,
)

internal fun SpokenLanguageResponse.toMovieDetails() = SpokenLanguage(
    englishName, name
)

data class GenreResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)

internal fun GenreResponse.toMovieDetails() = Genre(
    id, name
)


