package com.maschago.neugelbchallangeandroid.presentation.ui.sample

import com.maschago.neugelbchallangeandroid.domain.model.Movie


// Sample movie for preview
val sampleMovie = Movie(
    id = 13475,
    title = "Star Trek",
    posterUrl = "/lV5OpzAss1z06YNagOVap1I35mH.jpg",
    description = "The fate of the galaxy rests in the hands of bitter rivals. " +
            "One, James Kirk, is a delinquent, thrill-seeking Iowa farm boy. ",
    releaseDate = "2009-05-06"
)

// Sample list of movies for preview purposes
val sampleMoviesList = listOf(
    sampleMovie,
    Movie(
        id = 188927,
        title = "Star Trek Beyond",
        posterUrl = "/yOnd3XQIg7JBmu0UuBjZyLdsxQD.jpg",
        description = "The USS Enterprise crew explores the furthest reaches of uncharted space, " +
                "where they encounter a mysterious new enemy who puts them and everything the Federation stands for to the test.",
        releaseDate = "2016-07-07"
    ),
    Movie(
        id = 54138,
        title = "Star Trek Into Darkness",
        posterUrl = "/7XrRkhMa9lQ71RszzSyVrJVvhyS.jpg",
        description = "When the crew of the Enterprise is called back home, " +
                "they find an unstoppable force of terror from within their own organization has detonated the fleet and everything it stands for, " +
                "leaving our world in a state of crisis.  ",
        releaseDate = "2013-05-05"
    )
)

