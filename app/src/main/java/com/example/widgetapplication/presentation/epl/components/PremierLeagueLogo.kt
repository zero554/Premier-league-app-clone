package com.example.widgetapplication.presentation.epl.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun PremierLeagueLogo(modifier: Modifier = Modifier) {
    AsyncImage(
        model = "https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/" +
                "Premier_League_Logo.svg/560px-Premier_League_Logo.svg.png",
        contentDescription = null,
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(Color.White),
        modifier = modifier
    )
}