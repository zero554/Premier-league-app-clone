package com.example.widgetapplication.common

import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.widgetapplication.presentation.widget.FixtureWidgetUiState
import com.example.widgetapplication.presentation.widget.model.WidgetEvent
import kotlinx.serialization.json.Json

private val json = Json { ignoreUnknownKeys = true }

object FixtureWidgetStateHelper {
    fun setLoading(
        mutablePreferences: MutablePreferences,
        isLoading: Boolean
    ) {
        mutablePreferences[booleanPreferencesKey(IS_LOADING_KEY)] = isLoading
    }

    fun saveMatchWeek(
        mutablePreferences: MutablePreferences,
        matchWeek: String
    ) {
        mutablePreferences[stringPreferencesKey(MATCH_WEEK_KEY)] = matchWeek
    }

    fun isMatchWeekStored(
        preferences: Preferences,
        matchWeek: String
    ): Boolean {
        return preferences[stringPreferencesKey(MATCH_WEEK_KEY)] == matchWeek
    }

    fun saveFixtureState(
        mutablePreferences: MutablePreferences,
        fixtureWidgetUiState: FixtureWidgetUiState
    ) {
        mutablePreferences[stringPreferencesKey(FIXTURE_WIDGET_UI_STATE_KEY)] = json
            .encodeToString(
                serializer = FixtureWidgetUiState.serializer(),
                value = fixtureWidgetUiState
            )
    }

    fun getFixtureState(
        preferences: Preferences
    ): FixtureWidgetUiState {
        val defaultUiState = FixtureWidgetUiState(
            widgetEvents = listOf(
                WidgetEvent(
                    homeTeam = "",
                    homeScore = "",
                    awayTeam = "",
                    awayScore = ""
                )
            )
        )

        val fixtureWidgetUiStateString =
            preferences[stringPreferencesKey(FIXTURE_WIDGET_UI_STATE_KEY)]

        return json.decodeFromString(
            deserializer = FixtureWidgetUiState.serializer(),
            string = fixtureWidgetUiStateString ?: Json.encodeToString(
                serializer = FixtureWidgetUiState.serializer(),
                value = defaultUiState
            )
        )
    }

    const val FIXTURE_WIDGET_UI_STATE_KEY = "fixture_widget_ui_state_key"
    const val MATCH_WEEK_KEY = "match_week_key"
    const val IS_LOADING_KEY = "is_loading_key"
}