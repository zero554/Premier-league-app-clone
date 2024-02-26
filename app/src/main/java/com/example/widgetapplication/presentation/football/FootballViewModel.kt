package com.example.widgetapplication.presentation.football

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.widgetapplication.usecase.GetFootballEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class FootballViewModel @Inject constructor (
    private val getFootballDataUseCase: GetFootballEventsUseCase
) : ViewModel() {

    var footballUiState = MutableStateFlow<FootballUiState?>(null)
        private set

    init {
        getFootballEvents()
    }

    private fun getFootballEvents() {
        viewModelScope.launch {
            footballUiState.update { FootballUiState(isLoading = true) }
            getFootballDataUseCase().collect {
                it
                    .doOnError { throwable ->
                        footballUiState.update {
                            FootballUiState(
                                errorMessage = throwable.localizedMessage ?: "Unexpected error has occurred"
                            )
                        }
                    }
                    .doOnSuccess { events ->
                        footballUiState.update {
                            FootballUiState(events = events)
                        }
                    }
            }
        }
    }
}