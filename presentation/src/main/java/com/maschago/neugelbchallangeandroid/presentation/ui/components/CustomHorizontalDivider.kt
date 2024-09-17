package com.maschago.neugelbchallangeandroid.presentation.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomHorizontalDivider() {
    HorizontalDivider(
        modifier = Modifier.height(1.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.30f)
    )
}