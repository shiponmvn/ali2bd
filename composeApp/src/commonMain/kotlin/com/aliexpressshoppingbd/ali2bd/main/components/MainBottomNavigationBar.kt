package com.aliexpressshoppingbd.ali2bd.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aliexpressshoppingbd.ali2bd.main.navigation.MainNavigationDestinations
import com.aliexpressshoppingbd.ali2bd.main.navigation.MainNavigationItem

@Composable
fun MainBottomNavigationBar(
    items: List<MainNavigationItem>,
    currentDestination: MainNavigationDestinations?,
    onItemClick: (MainNavigationDestinations) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shadowElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(MaterialTheme.colorScheme.surface),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                AnimatedBottomNavItem(
                    item = item,
                    isSelected = currentDestination == item.destination,
                    onClick = { onItemClick(item.destination) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
