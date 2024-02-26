package com.example.widgetapplication.presentation.widget

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.widgetapplication.common.FixtureWidgetStateHelper
import com.example.widgetapplication.presentation.widget.components.Fixture
import com.example.widgetapplication.presentation.widget.components.PremierLeagueRoundBox
import java.time.Duration
import java.util.concurrent.TimeUnit


class FixtureWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        // Load data need to render Widget
        // Switch to different thread to run long running tasks

        provideContent {
            val fixtureWidgetUiState = FixtureWidgetStateHelper
                .getFixtureState(currentState())

            Log.d("FixtureWidgetWorker", "${FixtureWidgetUiState.toString()} in LunoPriceWidget")

            Fixtures(fixtureWidgetUiState)
        }
    }

    companion object {
        const val UNIQUE_WORK_TAG = "fixture_widget_work"
    }
}

@Composable
fun Fixtures(
    fixtureWidgetUiState: FixtureWidgetUiState
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = GlanceModifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        fixtureWidgetUiState.widgetEvents.forEach {
            with(it) {
                Fixture(
                    widgetEvent = this,
                    modifier = GlanceModifier
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .fillMaxWidth()
                ) {
                    PremierLeagueRoundBox {
                        Text(
                            text = "$homeScore - $awayScore",
                            style = TextStyle(
                                color = ColorProvider(Color.White),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = GlanceModifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun Context.startFixtureWorker(matchWeek: String = "26") {
    val netWorkConstraints = Constraints
        .Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val periodicWorkRequest = OneTimeWorkRequest
        .Builder(workerClass = FixtureWidgetWorker::class.java)
        .setConstraints(netWorkConstraints)
        .setInputData(FixtureWidgetWorker.buildData(matchWeek))
        .setBackoffCriteria(
            backoffPolicy = BackoffPolicy.LINEAR,
            backoffDelay = 5000L,
            timeUnit = TimeUnit.MILLISECONDS
        )
        .build()

    val uniqueWorkName = FixtureWidget.UNIQUE_WORK_TAG + "_$matchWeek"
    WorkManager
        .getInstance(this)
        .enqueueUniqueWork(
            uniqueWorkName,
            ExistingWorkPolicy.REPLACE,
            periodicWorkRequest
        )
}