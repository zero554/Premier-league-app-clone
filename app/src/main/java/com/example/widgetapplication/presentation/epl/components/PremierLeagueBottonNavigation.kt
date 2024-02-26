package com.example.widgetapplication.presentation.epl.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.widgetapplication.presentation.epl.BottomNavItem

@Composable
fun BottomNavigation(
    items: List<BottomNavItem>,
    modifier: Modifier = Modifier,
    selected: Int = 0,
    onBottomNavItemClick: (Int) -> Unit
) {
    NavigationBar(modifier = modifier) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                label = {
                        Text(text = item.title)
                },
                onClick = { onBottomNavItemClick(index) },
                icon = {
                    if (item.icon is ImageVector) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                    if (item.icon is String) {
                        AsyncImage(
                            model = item.icon,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            )
        }
    }
}