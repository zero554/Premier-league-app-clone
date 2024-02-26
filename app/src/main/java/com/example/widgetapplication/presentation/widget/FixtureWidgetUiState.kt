package com.example.widgetapplication.presentation.widget

import com.example.widgetapplication.presentation.widget.model.WidgetEvent
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class FixtureWidgetUiState(
    val widgetEvents: List<WidgetEvent> = listOf()
)