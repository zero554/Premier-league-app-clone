package com.example.widgetapplication

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.widgetapplication.domain.repository.FootballRepository
import com.example.widgetapplication.presentation.widget.FixtureWidgetWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FootballApplication: Application()

//
//class FixtureWorkerFactory @Inject constructor(
//    private val footballRepository: FootballRepository
//): WorkerFactory() {
//    override fun createWorker(
//        appContext: Context,
//        workerClassName: String,
//        workerParameters: WorkerParameters
//    ): ListenableWorker? {
//        return FixtureWidgetWorker(
//            context = appContext,
//            parameters = workerParameters,
//        )
//    }
//}