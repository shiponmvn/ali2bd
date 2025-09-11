package com.aliexpressshoppingbd.ali2bd.presentation.categories.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.ProductItem
import com.aliexpressshoppingbd.ali2bd.presentation.categories.presentation.components.*
import com.aliexpressshoppingbd.ali2bd.presentation.categories.presentation.viewmodel.CategoriesViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    viewModel: CategoriesViewModel,
    onNavigateBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedCategoryId = viewModel.selectedCategoryId

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Categories", style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                uiState.isCategoriesLoading && uiState.categories.isEmpty() -> {
                    CategoriesLoading()
                }

                uiState.categoriesError != null && uiState.categories.isEmpty() -> {
                    CategoriesError(
                        message = uiState.categoriesError ?: "Failed to load categories",
                        onRetry = { viewModel.retry() }
                    )
                }

                uiState.categories.isNotEmpty() -> {
                    CategoriesContent(
                        categories = uiState.categories,
                        selectedCategoryId = selectedCategoryId,
                        products = uiState.products,
                        isProductsLoading = uiState.isProductsLoading,
                        productsError = uiState.productsError,
                        onCategoryClick = { viewModel.selectCategory(it) },
                        onProductClick = { /* Handle product click */ },
                        onRetryLoadProducts = { viewModel.retryLoadingProducts() }
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoriesContent(
    categories: List<com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryItem>,
    selectedCategoryId: String?,
    products: List<ProductItem>,
    isProductsLoading: Boolean,
    productsError: String?,
    onCategoryClick: (String) -> Unit,
    onProductClick: (ProductItem) -> Unit,
    onRetryLoadProducts: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxSize()) {
        // Left side: Categories sidebar (30% width)
        CategoriesSidebar(
            categories = categories,
            selectedCategoryId = selectedCategoryId,
            onCategoryClick = onCategoryClick,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.3f)
        )

        // Right side: Products grid (70% width)
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.7f)
        ) {
            when {
                isProductsLoading -> {
                    CategoriesLoading()
                }

                productsError != null -> {
                    CategoriesError(
                        message = productsError,
                        onRetry = onRetryLoadProducts
                    )
                }

                products.isEmpty() -> {
                    CategoryProductsEmpty()
                }

                else -> {
                    CategoryProductsGrid(
                        products = products,
                        onProductClick = onProductClick
                    )
                }
            }
        }
    }
}
