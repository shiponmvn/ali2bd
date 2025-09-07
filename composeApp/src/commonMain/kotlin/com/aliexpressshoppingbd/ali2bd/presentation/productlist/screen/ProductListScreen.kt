package com.aliexpressshoppingbd.ali2bd.presentation.productlist.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.res.ProductItem
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.domain.model.ProductListModel
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.presentation.components.*
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.presentation.viewmodel.ProductListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel,
    keyword: String,
    onProductClick: (ProductItem) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(keyword) {
        if (keyword.isNotBlank()) {
            viewModel.searchProducts(keyword)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Search Results") },
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
                    ProductListLoading()
                }

                uiState.error != null -> {
                    ProductListError(
                        message = uiState.error!!,
                        onRetry = { viewModel.retry(keyword) }
                    )
                }

                uiState.products.isEmpty() && !uiState.isLoading -> {
                    ProductListEmpty()
                }

                else -> {
                    ProductListGrid(
                        products = uiState.products,
                        onProductClick = onProductClick
                    )
                }
            }
        }
    }
}
