package com.aliexpressshoppingbd.ali2bd.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WishlistIcon(
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = "Wishlist",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
