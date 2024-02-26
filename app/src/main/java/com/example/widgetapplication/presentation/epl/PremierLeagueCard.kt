package com.example.widgetapplication.presentation.epl

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.widgetapplication.common.Utils
import com.example.widgetapplication.common.Utils.getDayDateAndMonth
import com.example.widgetapplication.common.getAllEventDates
import com.example.widgetapplication.common.getEventsOnThisDate
import com.example.widgetapplication.data.remote.model.Event
import com.example.widgetapplication.presentation.epl.components.BoxComponent
import com.example.widgetapplication.presentation.epl.components.MatchWeekTitle
import com.example.widgetapplication.presentation.football.FootballUiState
import com.example.widgetapplication.ui.theme.premierLeagueGray
import com.example.widgetapplication.ui.theme.premierLeaguePurple
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PremierLeagueCard(
    footballUiState: FootballUiState,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray.copy(alpha = .2f)
        ),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.White)
        ) {
            footballUiState.events?.let {
                MatchWeekTitle(
                    matchWeek = "Matchweek ${it.events.first().intRound}",
                    color = Color.White,
                    hasBackground = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                )

                val annotatedString = buildAnnotatedString {
                    val size = 10.sp
                    val color = premierLeaguePurple.copy(alpha = .5f)

                    val span = SpanStyle(color = color, fontSize = size)
                    withStyle(style = span) {
                        append("All times shown are your ")
                    }

                    val spanStyle = SpanStyle(
                        color = color,
                        fontSize = size,
                        fontWeight = FontWeight.Bold
                    )
                    withStyle(spanStyle) {
                        append("local time")
                    }
                }

                Text(
                    text = annotatedString,
                    modifier = Modifier.padding(top = 16.dp)
                )

                val eventTimeDates = getAllEventDates(it.events)
                var date = ""
                repeat(eventTimeDates.size) { index ->
                    date = eventTimeDates[index]
                    val localDate = LocalDate.parse(date)

                    Text(
                        text = getDayDateAndMonth(localDate),
                        fontSize = 16.sp,
                        color = premierLeaguePurple,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    getEventsOnThisDate(
                        date = date,
                        events = it.events
                    ).forEach { event ->
                        if (event.strStatus == "Not Started") {
                            PremierLeagueFixture(
                                event = event,
                                modifier = Modifier
                                    .padding(top = 8.dp)
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
                        } else {
                            PremierLeagueFixtureUpdate(
                                event = event,
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }

                    val horizontalPadding = 8.dp

                    if (index != eventTimeDates.size - 1) {
                        Divider(
                            thickness = .5.dp,
                            color = premierLeagueGray.copy(alpha = .2f),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = horizontalPadding,
                                    end = horizontalPadding,
                                    bottom = if (index != eventTimeDates.size) 4.dp else 0.dp
                                )
                        )
                    }
                }
            }
        }
    }
}