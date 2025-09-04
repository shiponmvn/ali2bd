package com.aliexpressshoppingbd.ali2bd.main.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aliexpressshoppingbd.ali2bd.main.components.MainAppBar
import com.aliexpressshoppingbd.ali2bd.main.components.MainBottomNavigationBar
import com.aliexpressshoppingbd.ali2bd.main.screens.AccountScreen
import com.aliexpressshoppingbd.ali2bd.main.screens.CartScreen
import com.aliexpressshoppingbd.ali2bd.main.screens.CategoryScreen
import com.aliexpressshoppingbd.ali2bd.main.screens.HomeNav
import com.aliexpressshoppingbd.ali2bd.main.screens.HomeScreen
import com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.viewmodel.SearchViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.screen.SearchScreen
import org.koin.compose.koinInject

@Composable
fun MainNavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var currentDestination by remember { mutableStateOf<MainNavigationDestinations>(MainNavigationDestinations.Home) }
    var cartItemCount by remember { mutableStateOf(3) } // Example cart count
    var notificationCount by remember { mutableStateOf(5) } // Example notification count

    val navigationItems = listOf(
        MainNavigationItem(
            destination = MainNavigationDestinations.Home,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            label = "Home"
        ),
        MainNavigationItem(
            destination = MainNavigationDestinations.Category,
            selectedIcon = Icons.Filled.Category,
            unselectedIcon = Icons.Outlined.Category,
            label = "Categories"
        ),
        MainNavigationItem(
            destination = MainNavigationDestinations.Cart,
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
            label = "Cart",
            hasNotification = cartItemCount > 0,
            notificationCount = cartItemCount
        ),
        MainNavigationItem(
            destination = MainNavigationDestinations.Account,
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            label = "Account"
        )
    )

    Scaffold(
        modifier = modifier,
       /* topBar = {
            MainAppBar(
                onSearchClick = {
                    // Handle search click (no longer needed with integrated search)
                },
                onNotificationClick = {
                    // Handle notification click
                },
                onWishlistClick = {
                    // Handle wishlist/favorites click
                },
                notificationCount = notificationCount
            )
        },*/
        bottomBar = {
            MainBottomNavigationBar(
                items = navigationItems,
                currentDestination = currentDestination,
                onItemClick = { destination ->
                    currentDestination = destination
                    navController.navigate(destination) {
                        // Pop up to start destination to avoid building up a large stack
                        popUpTo(MainNavigationDestinations.Home) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = MainNavigationDestinations.Home.toString(),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            composable(route = MainNavigationDestinations.Home.toString()) {
                HomeScreen(navController = navController)
            }

            composable(route = MainNavigationDestinations.Category.toString()) {
                CategoryScreen()
            }

            composable(route = MainNavigationDestinations.Cart.toString()) {
                CartScreen()
            }

            composable(route = MainNavigationDestinations.Account.toString()) {
                AccountScreen()
            }

            composable(route = MainNavigationDestinations.Search.toString()) {
                val searchViewModel: SearchViewModel = koinInject()
                SearchScreen(

                )
            }
        }
    }
}
