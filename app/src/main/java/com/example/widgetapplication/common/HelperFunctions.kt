package com.example.widgetapplication.common

import com.example.widgetapplication.data.remote.model.Event

internal fun getEventsOnThisDate(date: String, events: List<Event>) = buildList {
    addAll(events.filter { it.dateEvent == date })
}

internal fun getAllEventDates(events: List<Event>) = buildList {
    events.forEach {
        if (!this.contains(it.dateEvent)) add(it.dateEvent)
    }
}

internal fun getUpcomingEvents(date: String, events: List<Event>) = buildList {
    addAll(getEventsOnThisDate(date, events))
}

internal fun getUpcomingEventsDate(events: List<Event>): String {
    events.forEach {
        if (it.strStatus == "Not Started") return it.dateEvent
    }

    return ""
}