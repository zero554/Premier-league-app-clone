package com.example.widgetapplication.usecase

import com.example.widgetapplication.common.Result
import com.example.widgetapplication.domain.repository.FootballRepository
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.flow

class GetFootballEventsUseCase @Inject constructor (
    private val footballRepository: FootballRepository
) {

    suspend operator fun invoke() = flow {
        try {
            val footballEvents = footballRepository.getFootballEventsData("26")
            emit(Result.Success(footballEvents))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            emit(Result.Error(e))
        }
    }
}