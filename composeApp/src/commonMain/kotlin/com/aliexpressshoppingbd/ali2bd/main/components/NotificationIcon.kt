package com.aliexpressshoppingbd.ali2bd.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotificationIcon(
    notificationCount: Int = 0,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    iconColor: Color = Color.White
) {
    Box(modifier = modifier) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )
        }

        if (notificationCount > 0) {
            Badge(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError,
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Text(
                    text = if (notificationCount > 99) "99+" else notificationCount.toString(),
                    fontSize = 10.sp
                )
            }
        }
    }
}
