package com.example.widgetapplication.presentation.widget.model

import kotlinx.serialization.Serializable

@Serializable
data class WidgetEvent(
    val homeTeam: String,
    val homeScore: String,
    val awayTeam: String,
    val awayScore: String
)
