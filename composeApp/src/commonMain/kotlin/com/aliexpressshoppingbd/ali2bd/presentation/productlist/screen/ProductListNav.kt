package com.aliexpressshoppingbd.ali2bd.presentation.productlist.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
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
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.PopUpToBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aliexpressshoppingbd.ali2bd.main.components.MainBottomNavigationBar
import com.aliexpressshoppingbd.ali2bd.main.navigation.MainNavigationDestinations
import com.aliexpressshoppingbd.ali2bd.main.navigation.MainNavigationItem
import com.aliexpressshoppingbd.ali2bd.main.screens.AccountScreen
import com.aliexpressshoppingbd.ali2bd.main.screens.CategoryScreen
import com.aliexpressshoppingbd.ali2bd.presentation.home.screen.HomeScreen
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.navigation.ProductListNavigation
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.presentation.viewmodel.ProductListViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.navigation.SearchNavigation
import com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.viewmodel.SearchViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.screen.SearchScreen
import org.koin.compose.koinInject

@Composable
fun ProductListNav(
    onNavigateBack: () -> Unit,
    keyword: String,
    )
   {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ProductListNavigation.ProductList,
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable<ProductListNavigation.ProductList> {
            val productListViewModel: ProductListViewModel = koinInject()
            ProductListScreen(
                viewModel = productListViewModel,
                onNavigateBack = onNavigateBack,
                keyword = keyword,
                onProductClick = {

                }
            )
        }


    }
}
