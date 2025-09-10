package com.aliexpressshoppingbd.ali2bd.main.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.ContactMail
import androidx.compose.material.icons.outlined.DocumentScanner
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Policy
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFF5F5F5))
    ) {
        // Profile Section with Avatar, Name, and Balance Info
        ProfileSection()

        // Orders Section
        OrdersSection()

        // Account Options Section
        AccountOptionsSection()

        // Services Section
        ServicesSection()
    }
}

@Composable
fun ProfileSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F0E8))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Profile Avatar
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Profile Picture",
                    tint = Color(0xFF6E91C9),
                    modifier = Modifier.size(60.dp)
                )
            }

            // User Info
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = "Shipon Sarder",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "+8801521239910",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )

                // Balance Information
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = "Refund Balance: ",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = "BDT 0.00",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFE05151),
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 2.dp)
                ) {
                    Text(
                        text = "Hold Balance: ",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = "BDT 0.00",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFE05151),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Settings Icon
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Settings",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { /* Handle settings click */ }
            )
        }
    }
}

@Composable
fun OrdersSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Orders Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "My Orders",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { /* Handle view all click */ }
                ) {
                    Text(
                        text = "View All",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFFE05151)
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = "View all orders",
                        tint = Color(0xFFE05151),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Order Status Items
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OrderStatusItem(
                    icon = Icons.Outlined.Payment,
                    label = "To Pay",
                    modifier = Modifier.weight(1f)
                )

                OrderStatusItem(
                    icon = Icons.Outlined.LocalShipping,
                    label = "In Transit",
                    modifier = Modifier.weight(1f)
                )

                OrderStatusItem(
                    icon = Icons.Outlined.ContactMail,
                    label = "Deliverable",
                    modifier = Modifier.weight(1f)
                )

                OrderStatusItem(
                    icon = Icons.Outlined.Check,
                    label = "Delivered",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Additional Order Options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OrderAdditionalOption(
                    icon = Icons.Outlined.DocumentScanner,
                    label = "My Request",
                    modifier = Modifier.weight(1f)
                )

                OrderAdditionalOption(
                    icon = Icons.Outlined.ShoppingBag,
                    label = "My Parcel",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun AccountOptionsSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Account Options Grid
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AccountOptionItem(
                    icon = Icons.Outlined.Place,
                    label = "Address",
                    modifier = Modifier.weight(1f)
                )

                AccountOptionItem(
                    icon = Icons.Outlined.EmojiEvents,
                    label = "Campaign",
                    modifier = Modifier.weight(1f)
                )

                AccountOptionItem(
                    icon = Icons.Outlined.Lock,
                    label = "Password",
                    modifier = Modifier.weight(1f)
                )

                AccountOptionItem(
                    icon = Icons.Outlined.Wallet,
                    label = "Wallet",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Second row of options
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AccountOptionItem(
                    icon = Icons.Outlined.Notifications,
                    label = "Notification",
                    modifier = Modifier.weight(1f)
                )

                AccountOptionItem(
                    icon = Icons.Outlined.Store,
                    label = "Shops",
                    modifier = Modifier.weight(1f)
                )

                AccountOptionItem(
                    icon = Icons.Outlined.Search,
                    label = "Action Needed",
                    modifier = Modifier.weight(1f)
                )

                AccountOptionItem(
                    icon = Icons.Outlined.Headphones,
                    label = "Help Center",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun ServicesSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Our Services",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Services Items
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ServiceItem(
                    icon = Icons.Outlined.Policy,
                    label = "Privacy Policy",
                    modifier = Modifier.weight(1f)
                )

                ServiceItem(
                    icon = Icons.Outlined.AttachMoney,
                    label = "Refund Policy",
                    modifier = Modifier.weight(1f)
                )

                ServiceItem(
                    icon = Icons.Outlined.Info,
                    label = "About Us",
                    modifier = Modifier.weight(1f)
                )

                ServiceItem(
                    icon = Icons.Outlined.LocalShipping,
                    label = "MoveOn Ship For Me",
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun OrderStatusItem(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { /* Handle order status click */ }
    ) {
        Box(
            modifier = Modifier
                .size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFFE05151),
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )
    }
}

@Composable
fun OrderAdditionalOption(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.clickable { /* Handle option click */ }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@Composable
fun AccountOptionItem(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { /* Handle account option click */ }
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFEFEF)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFFE05151),
                modifier = Modifier.size(20.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )
    }
}

@Composable
fun ServiceItem(icon: ImageVector, label: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable { /* Handle service click */ }
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFFE05151),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(horizontal = 2.dp)
        )
    }
}
