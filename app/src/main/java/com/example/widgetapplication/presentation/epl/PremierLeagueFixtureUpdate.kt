package com.example.widgetapplication.presentation.epl

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.widgetapplication.data.remote.model.Event
import com.example.widgetapplication.presentation.epl.components.BoxComponent
import com.example.widgetapplication.ui.theme.premierLeaguePurple

@Composable
fun PremierLeagueFixtureUpdate(
    event: Event,
    modifier: Modifier = Modifier
) {
    with(event) {
        PremierLeagueFixture(
            event = this,
            modifier = modifier
        ) {
            BoxComponent(
                borderColor = premierLeaguePurple,
                backgroundColor = premierLeaguePurple,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "${intHomeScore.orEmpty()} - ${intAwayScore.orEmpty()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

fun String?.orEmpty() = this ?: "0"