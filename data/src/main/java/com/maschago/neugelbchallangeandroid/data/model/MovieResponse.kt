package com.maschago.neugelbchallangeandroid.data.model

import com.google.gson.annotations.SerializedName
import com.maschago.neugelbchallangeandroid.domain.model.Movie

data class MovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)

data class MovieDto(
    @SerializedName("adult") val isAdult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int,
)


fun MovieDto.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        posterUrl = this.posterPath ?: this.backdropPath,
        description = this.overview,
        releaseDate = this.releaseDate.orEmpty()
    )
}
