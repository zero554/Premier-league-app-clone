package com.example.widgetapplication.presentation.epl.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.widgetapplication.ui.theme.premierLeaguePurple

@Composable
fun MatchWeekTitle(
    matchWeek: String,
    hasBackground: Boolean = false,
    color: Color = premierLeaguePurple,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        if (hasBackground) {
            AsyncImage(
                model = "https://footytrivia.com/wp-content/uploads/2023/03/impossible-" +
                        "premier-league-quiz-1.png",
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text(
            text = matchWeek,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}