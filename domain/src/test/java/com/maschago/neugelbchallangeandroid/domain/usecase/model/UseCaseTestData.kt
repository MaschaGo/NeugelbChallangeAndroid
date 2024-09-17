package com.maschago.neugelbchallangeandroid.domain.usecase.model

import com.maschago.neugelbchallangeandroid.domain.model.Genre
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import com.maschago.neugelbchallangeandroid.domain.model.MovieDetails
import com.maschago.neugelbchallangeandroid.domain.model.SpokenLanguage

internal object MovieTestData {
    val mockSampleMovieDetails = MovieDetails(
        adult = false,
        backdropPath = "/els3qltvcOnflTlU48iyfMPIctU.jpg",
        budget = 15000000,
        genres = listOf(
            Genre(18, "Drama"),
            Genre(14, "Fantasy")
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
        spokenLanguage = listOf(
            SpokenLanguage("Latin", "Latin"),
            SpokenLanguage("Russian", "Pусский"),
            SpokenLanguage("German", "Deutsch")
        ),
        title = "The Master and Margarita",
        voteAverage = 7.0f,
        voteCount = 52,
        releaseYear = "2024",
        originCountry = listOf("RU")
    )

    val mockMoviesList = listOf(
        Movie(
            id = 13475,
            title = "star trek",
            posterUrl = "/lV5OpzAss1z06YNagOVap1I35mH.jpg",
            description = "The fate of the galaxy rests in the hands of bitter rivals. " +
                    "One, James Kirk, is a delinquent, thrill-seeking Iowa farm boy. ",
            releaseDate = "2009-05-06"
        ), Movie(
            id = 957452,
            title = "The Crow",
            posterUrl = "/Asg2UUwipAdE87MxtJy7SQo08XI.jpg",
            description = "Soulmates Eric and Shelly are brutally murdered when the demons of her dark past catch up with them.",
            releaseDate = "2024-08-21"
        )
    )

}

