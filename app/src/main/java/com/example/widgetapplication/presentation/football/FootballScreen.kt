package com.example.widgetapplication.presentation.football

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.widgetapplication.common.Utils
import com.example.widgetapplication.common.getUpcomingEvents
import com.example.widgetapplication.common.getUpcomingEventsDate
import com.example.widgetapplication.data.remote.model.Event
import com.example.widgetapplication.presentation.epl.PremierLeagueCard
import com.example.widgetapplication.presentation.epl.PremierLeagueFixture
import com.example.widgetapplication.presentation.epl.PremierLeagueFixtureUpdate
import com.example.widgetapplication.presentation.epl.components.BoxComponent
import com.example.widgetapplication.presentation.epl.components.MatchWeekTitle
import com.example.widgetapplication.presentation.epl.components.PremierLeagueLogo
import com.example.widgetapplication.ui.theme.premierLeagueGray
import com.example.widgetapplication.ui.theme.premierLeagueOrange2019
import com.example.widgetapplication.ui.theme.premierLeaguePink2019
import com.example.widgetapplication.ui.theme.premierLeaguePurple
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FootballScreen(
    footballUiState: FootballUiState,
) {
    val scrollState = rememberScrollState()

    with(footballUiState) {
        events?.let {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(premierLeaguePink2019, premierLeagueOrange2019)
                        )
                    )
                    .verticalScroll(scrollState)
            ) {
                PremierLeagueLogo(
                    modifier = Modifier
                        .size(72.dp)
                )

                MatchWeekTitle(
                    matchWeek = "Matchweek Live",
                    color = Color.White
                )

                val upcomingEventsDate = getUpcomingEventsDate(it.events)

                Text(
                    text = if (upcomingEventsDate.isNotEmpty()) Utils.getDayDateAndMonth(
                        LocalDate.parse(
                            upcomingEventsDate
                        )
                    ) else "",
                    fontSize = 16.sp,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                getUpcomingEvents(upcomingEventsDate, it.events).forEach {
                    PremierLeagueFixtureCard(
                        event = it,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                PremierLeagueCard(
                    footballUiState = footballUiState,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(
                            vertical = 16.dp,
                            horizontal = 8.dp
                        )
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PremierLeagueFixtureCard(
    event: Event,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    ) {
        PremierLeagueFixture(
            event = event,
            showArrowForward = true,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        ) {
            BoxComponent(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
            {
                val (startTime, _) = Utils.getTimeAndDate(event.strTimestamp)

                Text(
                    text = startTime,
                    fontSize = 12.sp,
                    color = premierLeaguePurple,
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}