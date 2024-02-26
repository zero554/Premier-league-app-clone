package com.example.widgetapplication.presentation.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.widgetapplication.ui.theme.premierLeaguePurple

@Composable
fun PremierLeagueText(
    text: String,
    color: Color = premierLeaguePurple,
    modifier: GlanceModifier = GlanceModifier
) {

    Text(
        text = if (text == "Nottingham Forest") "Nott'm Forest" else text,
        style = TextStyle(
            fontSize = 12.sp,
            color = ColorProvider(color)
        ),
        modifier = modifier
    )
}