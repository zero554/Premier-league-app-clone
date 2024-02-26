package com.example.widgetapplication.domain.repository

import com.example.widgetapplication.domain.model.Events

interface FootballRepository {

    suspend fun getFootballEventsData(matchWeek: String): Events
}