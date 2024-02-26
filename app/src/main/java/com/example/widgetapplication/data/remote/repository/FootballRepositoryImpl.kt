package com.example.widgetapplication.data.remote.repository

import com.example.widgetapplication.data.remote.FootballApi
import com.example.widgetapplication.data.remote.model.toEvents
import com.example.widgetapplication.domain.model.Events
import com.example.widgetapplication.domain.repository.FootballRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FootballRepositoryImpl @Inject constructor (
    private val footballApi: FootballApi
) : FootballRepository {

    override suspend fun getFootballEventsData(matchWeek: String): Events = withContext(Dispatchers.IO) {
        footballApi.getFootballEventsData(round = matchWeek).toEvents()
    }
}