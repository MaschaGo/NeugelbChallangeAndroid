package com.maschago.neugelbchallangeandroid.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.maschago.neugelbchallangeandroid.presentation.ui.components.InformationView
import com.maschago.neugelbchallangeandroid.presentation.ui.components.LoadingView
import com.maschago.neugelbchallangeandroid.presentation.ui.components.MovieItem
import com.maschago.neugelbchallangeandroid.presentation.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onMovieClick: (Int) -> Unit,
) {
    // Use rememberSaveable to retain the search query across navigation and configuration changes
    var query by rememberSaveable { mutableStateOf("") }
    val searchResults = viewModel.searchResults.collectAsLazyPagingItems()

    Column {
        // Search Bar
        TextField(
            value = query,
            onValueChange = { newQuery ->
                query = newQuery
                if (query.length >= 3) {
                    viewModel.searchMovies(query)
                }
            },
            label = { Text("Search Movies") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            trailingIcon = {
                // Clear button
                if (query.isNotEmpty()) {
                    IconButton(onClick = {
                        query = ""
                        viewModel.clearSearch()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Clear search"
                        )
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Show search results using LazyColumn with Paging
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(count = searchResults.itemCount) { index ->
                // As the standard items call provides only the index, we get the item
                // directly from our lazyPagingItems
                val item = searchResults[index]
                item?.let { MovieItem(movie = it, onClick = { onMovieClick(it.id) }) }
            }

            // Handle loading and errors for paging
            searchResults.apply {
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                LoadingView(modifier = Modifier)
                            }
                        }
                    }

                    is LoadState.Error -> {
                        val error = searchResults.loadState.refresh as LoadState.Error
                        item {
                            InformationView(
                                modifier = Modifier,
                                message = "Error: ${error.error.localizedMessage}",
                                onButtonClick = { })
                        }
                    }

                    else -> {}
                }
            }
        }
    }
}
