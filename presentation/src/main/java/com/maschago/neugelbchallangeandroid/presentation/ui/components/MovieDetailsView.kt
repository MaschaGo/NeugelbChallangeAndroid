package com.maschago.neugelbchallangeandroid.presentation.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.maschago.neugelbchallangeandroid.core.ui.theme.NeugelbChallangeAndroidTheme
import com.maschago.neugelbchallangeandroid.domain.model.MovieDetails
import com.maschago.neugelbchallangeandroid.domain.model.genresAsString
import com.maschago.neugelbchallangeandroid.domain.model.originCountryAsString
import com.maschago.neugelbchallangeandroid.domain.model.spokenLanguageAsString
import com.maschago.neugelbchallangeandroid.presentation.BuildConfig
import com.maschago.neugelbchallangeandroid.presentation.ui.sample.sampleMovieDetails

@Composable
fun MovieDetailsView(movie: MovieDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top image (backdrop)
        movie.backdropPath?.let { backdropPath ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                model = BuildConfig.IMAGE_BASE_URL + backdropPath,
                contentDescription = movie.title,
                contentScale = ContentScale.Crop,
            )
        }

        CustomHorizontalDivider()

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Poster and movie details
            Row(modifier = Modifier.fillMaxWidth()) {

                movie.posterPath?.let { posterPath ->
                    AsyncImage(
                        modifier = Modifier
                            .width(100.dp)
                            .height(150.dp),
                        model = BuildConfig.IMAGE_BASE_URL + posterPath,
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Title and release year
                    SimpleText(
                        style = MaterialTheme.typography.titleMedium,
                        text = "${movie.title ?: "N/A"} (${movie.releaseYear}) ",
                    )

                    // Original title
                    TextLabelWithTitleHorizontal(
                        title = "Original Title:",
                        text = movie.originalTitle ?: "N/A"
                    )

                    // Genre
                    TextLabelWithTitleHorizontal(
                        title = "Genre:",
                        text = movie.genres.genresAsString()
                    )

                    // Release Date
                    TextLabelWithTitleHorizontal(
                        title = "Release Date:",
                        text = movie.releaseDate ?: "N/A"
                    )
                }
            }

            CustomHorizontalDivider()

            // Movie description (overview)
            SimpleText(
                text = movie.overview ?: "No description available.",
                textAlign = TextAlign.Justify,
            )

            CustomHorizontalDivider()

            // Country of origin and spoken language
            TextLabelWithTitleHorizontal(
                title = "Country of Origin:",
                text = movie.originCountry.originCountryAsString()
            )
            CustomHorizontalDivider()
            TextLabelWithTitleHorizontal(
                title = "Spoken Language(s):",
                text = movie.spokenLanguage.spokenLanguageAsString()
            )
        }
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF121212, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMovieDetailsView() {
    NeugelbChallangeAndroidTheme {
        MovieDetailsView(movie = sampleMovieDetails)
    }
}
