package com.example.widgetapplication.presentation.epl.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.widgetapplication.ui.theme.premierLeagueGray

@Composable
fun BoxComponent(
    modifier: Modifier = Modifier,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = Color.Unspecified,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .then(
                Modifier
                    .background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .border(
                        width = .5.dp,
                        color = borderColor,
                        shape = RoundedCornerShape(4.dp)
                    )
            )
    ) {
        content()
    }
}