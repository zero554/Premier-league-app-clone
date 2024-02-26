package com.example.widgetapplication.presentation.epl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.widgetapplication.common.Utils
import com.example.widgetapplication.data.remote.model.Event
import com.example.widgetapplication.presentation.epl.components.TeamCrest
import com.example.widgetapplication.ui.theme.premierLeaguePurple

@Composable
fun PremierLeagueFixture(
    event: Event,
    modifier: Modifier = Modifier,
    showArrowForward: Boolean = false,
    boxComponent: @Composable () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.weight(1f)
            ) {
                val homeTeam = event.strHomeTeam
                Text(
                    text = if (homeTeam == "Nottingham Forest") "Nott'm Forest" else homeTeam,
                    fontSize = 12.sp,
                    color = premierLeaguePurple
                )

                Spacer(modifier = Modifier.width(8.dp))

                TeamCrest(
                    url = Utils.getTeamCrest(event.strHomeTeam)
                )
            }

            boxComponent()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                TeamCrest(
                    url = Utils.getTeamCrest(event.strAwayTeam)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = event.strAwayTeam,
                    fontSize = 10.sp,
                    color = premierLeaguePurple
                )

                if (showArrowForward) {
                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = null,
                            tint = premierLeaguePurple,
                            modifier = Modifier.size(10.dp)
                        )
                    }
                }
            }

        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = "https://upload.wikimedia.org/wikipedia/en/6/66/Supersport2012.jpg",
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(48.dp)
            )
        }
    }
}