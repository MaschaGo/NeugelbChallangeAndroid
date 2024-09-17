package com.maschago.neugelbchallangeandroid.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maschago.neugelbchallangeandroid.presentation.navigation.Screen

@Composable
fun CustomAppBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    title: String = "",
    containerColor: Color = MaterialTheme.colorScheme.background,
    titleContentColor: Color = MaterialTheme.colorScheme.onBackground,
) {
    // Observe the current back stack entry to trigger recomposition when the navigation state changes
    val backStackEntry by navController.currentBackStackEntryAsState()

    // Show navigation icon (back button) only if there is a previous entry in the back stack
    val showNavIcon = backStackEntry?.destination?.route != Screen.Movies.route &&
            navController.previousBackStackEntry != null

    // Show search icon only when not in the details screen
    val showSearchIcon = backStackEntry?.destination?.route == Screen.Movies.route

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(containerColor)
            .statusBarsPadding()
    ) {
        if (showNavIcon) {
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = titleContentColor,
                    contentDescription = "Back button"
                )
            }
        } else {
            // Reserve space for the navigation icon even when it's not visible
            Spacer(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterStart)
            )
        }

        // Centered title, independent of the nav icon
        Text(
            text = title,
            color = titleContentColor,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
        )

        // Conditionally show the Search Icon aligned to the right
        if (showSearchIcon) {
            IconButton(
                onClick = {
                    navController.navigate(Screen.Search.route)
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = titleContentColor,
                    contentDescription = "Search"
                )
            }
        } else {
            // Reserve space for the search icon even when it's not visible
            Spacer(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}
