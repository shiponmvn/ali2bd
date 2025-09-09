package com.aliexpressshoppingbd.ali2bd.presentation.cart.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aliexpressshoppingbd.ali2bd.presentation.cart.presentation.components.*
import com.aliexpressshoppingbd.ali2bd.presentation.cart.presentation.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onNavigateBack: () -> Unit,
    onCheckout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Cart") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    CartLoadingComponent()
                }

                uiState.error != null -> {
                    CartErrorComponent(
                        message = uiState.error!!,
                        onRetry = { viewModel.getCartItems() }
                    )
                }

                uiState.cart?.data?.isEmpty() == true -> {
                    Box(modifier = Modifier)
                }

                else -> {
                    uiState.cart?.let { cart ->
                        CartContentComponent(
                            cart = cart.data,
                            onQuantityChange = { itemId, quantity ->
                                viewModel.updateCartItem(itemId, quantity)
                            },
                            onRemoveItem = { itemId ->
                                viewModel.removeFromCart(itemId)
                            },
                            onCheckout = onCheckout
                        )
                    }
                }
            }
        }
    }
}
