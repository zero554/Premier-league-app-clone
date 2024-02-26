package com.example.widgetapplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.widgetapplication.presentation.epl.BottomNavItem
import com.example.widgetapplication.presentation.football.FootballViewModel
import com.example.widgetapplication.presentation.epl.PremierLeagueCard
import com.example.widgetapplication.presentation.football.FootballScreen
import com.example.widgetapplication.presentation.widget.startFixtureWorker
import com.example.widgetapplication.ui.theme.WidgetApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<FootballViewModel>()
            val footballUiState by viewModel.footballUiState.collectAsState()
            val scrollState = rememberScrollState()

            startFixtureWorker()

            WidgetApplicationTheme {
                footballUiState?.let {
                    FootballScreen(it)
                }
            }
        }
    }
}