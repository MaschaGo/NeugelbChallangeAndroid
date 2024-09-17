package com.maschago.neugelbchallangeandroid.data.utils

import com.maschago.neugelbchallangeandroid.data.model.GenreResponse
import com.maschago.neugelbchallangeandroid.data.model.MovieDetailsDto
import com.maschago.neugelbchallangeandroid.data.model.MovieDto
import com.maschago.neugelbchallangeandroid.data.model.MovieResponse
import com.maschago.neugelbchallangeandroid.data.model.SpokenLanguageResponse
import retrofit2.Response

internal object MovieTestData {
    val sampleMovieDetails: Response<MovieDetailsDto> = Response.success(
        MovieDetailsDto(
            isAdult = false,
            backdropPath = "/els3qltvcOnflTlU48iyfMPIctU.jpg",
            budget = 15000000,
            genre = listOf(
                GenreResponse(18, "Drama"),
                GenreResponse(14, "Fantasy")
            ),
            homepage = "",
            id = 586353,
            imdbId = "tt14536120",
            originalLanguage = "ru",
            originalTitle = "Мастер и Маргарита",
            overview = "Moscow, 1930s. A prominent writer's works are suddenly censored by the Soviet state and the premiere of his theatrical play about Pontius Pilate is canceled. He's kicked out of the Soviet Writer's Union, and quickly turns into an outcast with no means to survive. Inspired by Margarita - his lover, he begins working on a new novel in which all the characters are satirically reinterpreted from his life. The novel's central character is Woland - a mystical dark force who visits Moscow to revenge all those who caused the writer's downfall. As the Master sinks himself deeper and deeper into his novel, adding himself and Margarita as characters, he gradually stops noticing as the border between reality and his imagination fades away.",
            popularity = 16.297,
            posterPath = "/jw172VAlwfYtWNWvhVrXZPW3jGT.jpg",
            releaseDate = "2024-01-25",
            revenue = 0,
            spokenLanguageResponses = listOf(
                SpokenLanguageResponse("Latin", "Latin"),
                SpokenLanguageResponse("Russian", "Pусский"),
                SpokenLanguageResponse("German", "Deutsch")
            ),
            title = "The Master and Margarita",
            voteAverage = 7.0f,
            voteCount = 52,
            originCountry = listOf("RU"),
        )
    )

    val sampleMoviesList: Response<MovieResponse> = Response.success(
        MovieResponse(
            page = 1,
            results = listOf(
                MovieDto(
                    isAdult = false,
                    backdropPath = "/q7M0JpPixbEYT8EhnI7wTEMONxz.jpg",
                    genreIds = listOf(878, 28, 12),
                    id = 13475,
                    originalLanguage = "en",
                    originalTitle = "Star Trek",
                    overview = "The fate of the galaxy rests in the hands of bitter rivals. One, James Kirk, is a delinquent, thrill-seeking Iowa farm boy. The other, Spock, a Vulcan, was raised in a logic-based society that rejects all emotion. As fiery instinct clashes with calm reason, their unlikely but powerful partnership is the only thing capable of leading their crew through unimaginable danger, boldly going where no one has gone before. The human adventure has begun again.",
                    popularity = 56.593,
                    posterPath = "/lV5OpzAss1z06YNagOVap1I35mH.jpg",
                    releaseDate = "2009-05-06",
                    title = "Star Trek",
                    voteAverage = 7.429,
                    voteCount = 9753
                ),
                MovieDto(
                    isAdult = false,
                    backdropPath = "/m4F1KRK5jAoQHi2mKDFE2jFKEIb.jpg",
                    genreIds = listOf(28, 12, 878),
                    id = 188927,
                    originalLanguage = "en",
                    originalTitle = "Star Trek Beyond",
                    overview = "The USS Enterprise crew explores the furthest reaches of uncharted space, where they encounter a mysterious new enemy who puts them and everything the Federation stands for to the test.",
                    popularity = 35.197,
                    posterPath = "/yOnd3XQIg7JBmu0UuBjZyLdsxQD.jpg",
                    releaseDate = "2016-07-07",
                    title = "Star Trek Beyond",
                    voteAverage = 6.784,
                    voteCount = 6538
                ),
                MovieDto(
                    isAdult = false,
                    backdropPath = "/wygUDDRNpeKUnkekRGeLCZM93tA.jpg",
                    genreIds = listOf(878, 28, 12, 53),
                    id = 199,
                    originalLanguage = "en",
                    originalTitle = "Star Trek: First Contact",
                    overview = "The Borg, a relentless race of cyborgs, are on a direct course for Earth. Violating orders to stay away from the battle, Captain Picard and the crew of the newly-commissioned USS Enterprise E pursue the Borg back in time to prevent the invaders from changing Federation history and assimilating the galaxy.",
                    popularity = 22.871,
                    posterPath = "/vrC1lkTktFQ4AqBfqf4PXoDDLcw.jpg",
                    releaseDate = "1996-11-22",
                    title = "Star Trek: First Contact",
                    voteAverage = 7.295,
                    voteCount = 1727
                )
            ),
            totalPages = 1,
            totalResults = 3
        )
    )

}

