package com.aliexpressshoppingbd.ali2bd.presentation.cart.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aliexpressshoppingbd.ali2bd.presentation.cart.navigation.CartNavigation
import com.aliexpressshoppingbd.ali2bd.presentation.cart.presentation.viewmodel.CartViewModel
import org.koin.compose.koinInject

@Composable
fun CartNav(
    onNavigateBack: () -> Unit,
    onCheckout: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = CartNavigation.Cart,
        modifier = Modifier
            .fillMaxSize()
    ) {
        composable<CartNavigation.Cart> {
            val cartViewModel: CartViewModel = koinInject()
            CartScreen(
                viewModel = cartViewModel,
                onNavigateBack = onNavigateBack,
                onCheckout = onCheckout
            )
        }
    }
}
