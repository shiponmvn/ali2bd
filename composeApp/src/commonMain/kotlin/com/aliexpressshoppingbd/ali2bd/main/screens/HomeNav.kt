package com.aliexpressshoppingbd.ali2bd.main.screens

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.aliexpressshoppingbd.ali2bd.main.components.MainAppBar
import com.aliexpressshoppingbd.ali2bd.main.components.MainBottomNavigationBar
import com.aliexpressshoppingbd.ali2bd.main.navigation.MainNavigationDestinations
import com.aliexpressshoppingbd.ali2bd.main.navigation.MainNavigationItem
import com.aliexpressshoppingbd.ali2bd.main.screens.AccountScreen
import com.aliexpressshoppingbd.ali2bd.main.screens.CartScreen
import com.aliexpressshoppingbd.ali2bd.main.screens.CategoryScreen
import com.aliexpressshoppingbd.ali2bd.main.screens.HomeScreen
import com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.viewmodel.SearchViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.screen.SearchScreen
import org.koin.compose.koinInject

@Composable
fun HomeNav(

) {
   /* val navigator = rememberNavController()
    NavHost(
        startDestination = MainNavigationDestinations.Home,
        navController = navigator,
        modifier = Modifier.fillMaxSize()
    ) {

        composable<MainNavigationDestinations.Home> {
            HomeScreen()
        }
        composable<MainNavigationDestinations.Search> {

            SearchScreen(
                navigateToMain = {
                    navigator.popBackStack()
                    navigator.navigate()
                }
            )
        }

    }*/
}
