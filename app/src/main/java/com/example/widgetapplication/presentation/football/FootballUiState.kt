package com.example.widgetapplication.presentation.football

import com.example.widgetapplication.domain.model.Events

data class FootballUiState(
    val isLoading: Boolean = false,
    val events: Events? = null,
    val errorMessage: String = ""
)
