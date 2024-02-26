package com.example.widgetapplication.data.remote.model


import com.example.widgetapplication.domain.model.Events
import com.google.gson.annotations.SerializedName

data class EventsDto(
    @SerializedName("events")
    val events: List<Event>
)

fun EventsDto.toEvents() = Events(events = events)