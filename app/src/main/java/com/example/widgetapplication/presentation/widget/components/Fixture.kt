package com.example.widgetapplication.presentation.widget.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceModifier
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.example.widgetapplication.presentation.widget.model.WidgetEvent
import com.example.widgetapplication.ui.theme.premierLeaguePurple

@Composable
fun Fixture(
    widgetEvent: WidgetEvent,
    modifier: GlanceModifier = GlanceModifier,
    boxContent: @Composable () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.End,
            modifier = GlanceModifier
                .defaultWeight()
        ) {
            PremierLeagueText(text = widgetEvent.homeTeam)
            // Team crest
        }

        Spacer(modifier = GlanceModifier.width(8.dp))
        boxContent()
        Spacer(modifier = GlanceModifier.width(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = GlanceModifier
                .defaultWeight()
        ) {
            // Team crest
            PremierLeagueText(text = widgetEvent.awayTeam)
        }
    }
}