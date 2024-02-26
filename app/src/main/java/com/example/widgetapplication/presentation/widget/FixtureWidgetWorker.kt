package com.example.widgetapplication.presentation.widget

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.MutablePreferences
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.widgetapplication.common.FixtureWidgetStateHelper
import com.example.widgetapplication.presentation.widget.model.WidgetEvent
import kotlinx.coroutines.CancellationException

class FixtureWidgetWorker (
    private val context: Context,
    parameters: WorkerParameters,
): CoroutineWorker(context, parameters) {

    override suspend fun doWork(): Result {
        return getMatchWeek()?.let { matchWeek ->
            val glanceId = GlanceAppWidgetManager(context)
                .getGlanceIds(FixtureWidget::class.java).firstOrNull() ?: return Result.failure()

            updateWidgetState(glanceId) {
                FixtureWidgetStateHelper.setLoading(it, true)
            }

            return try {
                val widgetEvents = listOf(
                    WidgetEvent(
                        homeTeam = "Liverpool",
                        homeScore = "6",
                        awayTeam = "Chelsea",
                        awayScore = "1"
                    ),
                    WidgetEvent(
                        homeTeam = "Fulham",
                        homeScore = "2",
                        awayTeam = "Brentford",
                        awayScore = "1"
                    ),
                    WidgetEvent(
                        homeTeam = "Aston Villa",
                        homeScore = "4",
                        awayTeam = "Brighton",
                        awayScore = "2"
                    )
                )

                val fixtureWidgetUiState = FixtureWidgetUiState(widgetEvents)
                updateWidgetState(glanceId = glanceId) {
                    FixtureWidgetStateHelper.saveFixtureState(
                        mutablePreferences = it,
                        fixtureWidgetUiState = fixtureWidgetUiState
                    )
                }

                Log.d("FixtureWidgetWorker", "Success")
                Log.d("FixtureWidgetWorker", widgetEvents.toString())
                Result.success()
            } catch (exception: Exception) {
                if (exception is CancellationException) throw exception
                else {
                    updateWidgetState(glanceId) {
                        FixtureWidgetStateHelper.setLoading(it, false)
                    }

                    Log.d("FixtureWidgetWorker", exception.message ?: "ERROR!!!")
                    Result.failure(workDataOf(
                        "Problems" to exception.message
                    ))
                }
            }
        } ?: Result.failure()
    }

    private suspend fun updateWidgetState(
        glanceId: GlanceId,
        update: (MutablePreferences) -> Unit
    ) {
        FixtureWidget().apply {
            updateAppWidgetState(
                context = context,
                glanceId = glanceId
            ) {
                update(it)
            }
            update(
                context,
                glanceId
            )
        }
    }

    private fun getMatchWeek(): String? {
        return inputData.getString(FixtureWidgetStateHelper.MATCH_WEEK_KEY)
    }

    companion object {
        fun buildData(matchWeek: String) = workDataOf(
            FixtureWidgetStateHelper.MATCH_WEEK_KEY to matchWeek
        )
    }
}