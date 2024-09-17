package com.maschago.neugelbchallangeandroid.presentation.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.maschago.neugelbchallangeandroid.core.ui.theme.NeugelbChallangeAndroidTheme
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import com.maschago.neugelbchallangeandroid.presentation.ui.sample.sampleMoviesList
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieList(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit,
) {
    val loadState = movies.loadState

    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(count = movies.itemCount) { index ->
                val item = movies[index]
                item?.let { MovieItem(movie = it, onClick = { onMovieClick(it.id) }) }
            }

            // Append Loading Indicator
            if (loadState.append is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingView(modifier)
                    }
                }
            }

            // Append Error Handling
            (loadState.append as? LoadState.Error)?.let {
                item {
                    InformationView(
                        modifier,
                        message = "Error: ${it.error.localizedMessage}",
                        onButtonClick = { movies.retry() })
                }
            }
        }

        // Initial Loading Overlay
        if (loadState.refresh is LoadState.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                LoadingView(modifier)
            }
        }

        // Initial Load Error Handling
        (loadState.refresh as? LoadState.Error)?.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ) {
                InformationView(
                    modifier,
                    message = "Error: ${it.error.localizedMessage}",
                    onButtonClick = { movies.retry() })
            }
        }
    }
}


@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode",showBackground = true, backgroundColor = 0xFF121212, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMovieList() {
    val pagingData = flowOf(PagingData.from(sampleMoviesList))
    val lazyPagingItems = pagingData.collectAsLazyPagingItems()

    NeugelbChallangeAndroidTheme {
        MovieList(
            movies = lazyPagingItems,
            onMovieClick = {}
        )
    }
}






