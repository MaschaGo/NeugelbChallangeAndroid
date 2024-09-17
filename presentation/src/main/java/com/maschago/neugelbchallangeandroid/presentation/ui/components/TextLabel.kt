package com.maschago.neugelbchallangeandroid.presentation.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maschago.neugelbchallangeandroid.core.ui.theme.NeugelbChallangeAndroidTheme

@Composable
fun SimpleText(
    text: String = "",
    style: TextStyle = MaterialTheme.typography.bodySmall,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        style = style,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun TextLabelWithTitleHorizontal(
    title: String = "",
    text: String = "",
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SimpleText(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
        )
        SimpleText(text = text)
    }
}

@Composable
fun TextLabelWithTitleVertical(
    title: String = "",
    text: String = "",

    ) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        SimpleText(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
        )
        SimpleText(text = text)
    }
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF121212, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextVerticalPreview() = NeugelbChallangeAndroidTheme {
    TextLabelWithTitleVertical(
        title = "I am a title",
        text = "Lore ipsum"
    )
}

@Preview(name = "Light Mode", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode", showBackground = true, backgroundColor = 0xFF121212, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TextHorizontalPreview() = NeugelbChallangeAndroidTheme {
    TextLabelWithTitleHorizontal(
        title = "I am a title vertical",
        text = "Lore ipsum"
    )
}
