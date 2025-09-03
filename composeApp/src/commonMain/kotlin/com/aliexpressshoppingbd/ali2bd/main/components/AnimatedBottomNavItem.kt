package com.aliexpressshoppingbd.ali2bd.main.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliexpressshoppingbd.ali2bd.main.navigation.MainNavigationItem

@Composable
fun AnimatedBottomNavItem(
    item: MainNavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    // Animation states
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.0f else 0.95f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessHigh
        ),
        label = "scale"
    )

    val iconColor by animateColorAsState(
        targetValue = if (isSelected)
            MaterialTheme.colorScheme.primary
        else
            MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = spring(dampingRatio = 0.8f),
        label = "iconColor"
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected)
            MaterialTheme.colorScheme.primary
        else
            MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = spring(dampingRatio = 0.8f),
        label = "textColor"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() }
            .padding(vertical = 8.dp)
            .scale(scale)
    ) {
        Box {
            Icon(
                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                contentDescription = item.label,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )

            // Badge for notifications
            if (item.hasNotification && item.notificationCount > 0) {
                Badge(
                    modifier = Modifier.align(Alignment.TopEnd),
                    containerColor = MaterialTheme.colorScheme.error
                ) {
                    Text(
                        text = if (item.notificationCount > 99) "99+" else item.notificationCount.toString(),
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onError
                    )
                }
            }
        }

        Text(
            text = item.label,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
