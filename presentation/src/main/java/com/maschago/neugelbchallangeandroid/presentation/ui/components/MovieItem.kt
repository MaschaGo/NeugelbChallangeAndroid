package com.maschago.neugelbchallangeandroid.presentation.ui.components


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.maschago.neugelbchallangeandroid.core.ui.theme.NeugelbChallangeAndroidTheme
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import com.maschago.neugelbchallangeandroid.presentation.BuildConfig
import com.maschago.neugelbchallangeandroid.presentation.R
import com.maschago.neugelbchallangeandroid.presentation.ui.sample.sampleMovie

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .border(shape = RoundedCornerShape(16.dp), width = 1.dp, color = MaterialTheme.colorScheme.outline)
            .background(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.30f), shape = RoundedCornerShape(16.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier =  modifier.padding(bottom = 8.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            Box(
                modifier = modifier
                    .height(250.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Column {
                    AsyncImage(
                        modifier = modifier.fillMaxWidth(),
                        model = BuildConfig.IMAGE_BASE_URL + movie.posterUrl,
                        contentDescription = movie.title,
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(R.drawable.ic_image),
                        error = painterResource(R.drawable.ic_broken_image)

                    )
                }
            }

            Text(
                modifier = modifier.padding(top = 8.dp),
                text = movie.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode",showBackground = true, backgroundColor = 0xFF121212, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMovieItem() {
    NeugelbChallangeAndroidTheme {
        MovieItem(movie = sampleMovie, onClick = {})
    }
}
