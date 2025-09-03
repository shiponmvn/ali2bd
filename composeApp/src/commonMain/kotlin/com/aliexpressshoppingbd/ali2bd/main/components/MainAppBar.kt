package com.aliexpressshoppingbd.ali2bd.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainAppBar(
    onSearchClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onWishlistClick: () -> Unit = {},
    notificationCount: Int = 0,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFFF6B35), // Orange start
                        Color(0xFFFF4500)  // Orange red end
                    )
                )
            )
            .padding(16.dp)
    ) {
        // First row: Logo and Icons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ali2BD",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 24.sp
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                WishlistIcon(
                    onClick = onWishlistClick
                )

                Spacer(modifier = Modifier.width(4.dp))

                NotificationIcon(
                    notificationCount = notificationCount,
                    onClick = onNotificationClick,
                    iconColor = Color.White
                )
            }
        }

        // Second row: Tagline
        Text(
            text = "Get Global Products At The Best Prices",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        // Third row: Search bar
        CustomSearchField(
            onSearchQueryChange = { query ->
                // Handle search query change
            },
            placeholder = "Search from million product or paste link"
        )
    }
}
