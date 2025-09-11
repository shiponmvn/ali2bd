package com.aliexpressshoppingbd.ali2bd.presentation.productlist.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.navigation.ProductListNavigation
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.presentation.viewmodel.ProductListViewModel
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
