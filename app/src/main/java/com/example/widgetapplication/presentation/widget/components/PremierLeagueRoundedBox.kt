package com.example.widgetapplication.presentation.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.ImageProvider
import androidx.glance.background
import androidx.glance.layout.Box
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.widgetapplication.R

const val FAKE_SCORE = "4 - 1"

@Composable
fun PremierLeagueRoundBox(
    modifier: GlanceModifier = GlanceModifier,
    background: ImageProvider = ImageProvider(R.drawable.shape_widget_border),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier.then(
            GlanceModifier
                .background(background)
        )
    ) {
        content()
    }
}