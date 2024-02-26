package com.example.widgetapplication.data.remote

import com.example.widgetapplication.data.remote.model.EventsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("json/3/eventsround.php?id=4328")
    suspend fun getFootballEventsData(
        @Query("s") season: String = "2023-2024",
        @Query("r") round: String = "26"
    ): EventsDto

    companion object {
        const val API_KEY = "60130162"
        const val BASE_URL = "https://www.thesportsdb.com/api/v1/"
    }
}