package com.aliexpressshoppingbd.ali2bd.presentation.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.aliexpressshoppingbd.ali2bd.main.components.CategoriesSection
import com.aliexpressshoppingbd.ali2bd.main.components.FeaturedProductsSection
import com.aliexpressshoppingbd.ali2bd.main.components.HomeHeader
import com.aliexpressshoppingbd.ali2bd.main.components.HomeSearchSection
import com.aliexpressshoppingbd.ali2bd.main.components.PromotionalBannerSection
import com.aliexpressshoppingbd.ali2bd.presentation.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigateToSearch: () -> Unit
) {
    // Keep cart count in remember to avoid recomposition storm
    var cartItemCount by remember { mutableStateOf(3) }

    // Collect product state only once
    val uiProductSectionUiState by viewModel.uiProductSectionUiState.collectAsStateWithLifecycle()
    val uiCategoryState by viewModel.uiCategoryState.collectAsStateWithLifecycle()
    val uiPromoBannerState by viewModel.uiPromoBannerState.collectAsStateWithLifecycle()

    // Remember each section separately to avoid unnecessary recomposition
    val section1 = remember(uiProductSectionUiState.section1) { uiProductSectionUiState.section1 }
    val section2 = remember(uiProductSectionUiState.section2) { uiProductSectionUiState.section2 }
    val section3 = remember(uiProductSectionUiState.section3) { uiProductSectionUiState.section3 }
    val section4 = remember(uiProductSectionUiState.section4) { uiProductSectionUiState.section4 }

    // Create a LazyListState to better control scrolling
    val lazyListState = rememberLazyListState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HomeHeader(
                userName = "User",
                cartItemCount = cartItemCount,
                onCartClick = {
                    // Navigate to cart
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier.fillMaxSize().padding(paddingValues).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),

            ) {
            // Search bar


            HomeSearchSection(
                onSearchClick = navigateToSearch,
                onVoiceClick = { /* Handle voice search */ },
            )


            CategoriesSection(
                categories = uiCategoryState.categories,
                onCategoryClick = { category ->
                    // Navigate to category page
                },
                onViewAllClick = {
                    // Navigate to all categories
                }
            )

            PromotionalBannerSection(
                banners = uiPromoBannerState.banners,
                onBannerClick = { banner ->
                    // Navigate to sale page
                }
            )
            if (section1 != null) {
                FeaturedProductsSection(
                    productSectionModel = section1,
                    onProductClick = { product ->
                        // Navigate to product details
                    },
                    onWishlistClick = { product ->
                        // Toggle wishlist
                    },
                    onViewAllClick = {
                        // Navigate to all featured products
                    }
                )
            }
            if (section2 != null) {
                FeaturedProductsSection(
                    productSectionModel = section2,
                    onProductClick = { product ->
                        // Navigate to product details
                    },
                    onWishlistClick = { product ->
                        // Toggle wishlist
                    },
                    onViewAllClick = {
                        // Navigate to all featured products
                    }
                )
            }

            if (section3 != null) {
                FeaturedProductsSection(
                    productSectionModel = section3,
                    onProductClick = { product ->
                        // Navigate to product details
                    },
                    onWishlistClick = { product ->
                        // Toggle wishlist
                    },
                    onViewAllClick = {
                        // Navigate to all featured products
                    }
                )
            }
            if (section4 != null) {
                FeaturedProductsSection(
                    productSectionModel = section4,
                    onProductClick = { product ->
                        // Navigate to product details
                    },
                    onWishlistClick = { product ->
                        // Toggle wishlist
                    },
                    onViewAllClick = {
                        // Navigate to all featured products
                    }
                )
            }


        }
    }
}
