package com.aliexpressshoppingbd.ali2bd.presentation.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
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

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Header
        item(key = "header") {
            key("header_key") {
                HomeHeader(
                    userName = "User",
                    cartItemCount = cartItemCount,
                    onCartClick = {
                        // Navigate to cart
                    }
                )
            }
        }

        // Search bar
        item(key = "HomeSearchSection") {
            key("search_key") {
                HomeSearchSection(
                    onSearchClick = navigateToSearch,
                    onVoiceClick = { /* Handle voice search */ },
                    onMenuClick = { /* Handle menu click */ }
                )
            }
        }

        // Categories
        item(key = "CategoriesSection") {
            key(uiCategoryState.categories.hashCode()) {
                CategoriesSection(
                    categories = uiCategoryState.categories,
                    onCategoryClick = { category ->
                        // Navigate to category page
                    },
                    onViewAllClick = {
                        // Navigate to all categories
                    }
                )
            }
        }

        // Promo banners
        item(key = "PromotionalBannerSection") {
            key(uiPromoBannerState.banners.hashCode()) {
                PromotionalBannerSection(
                    banners = uiPromoBannerState.banners,
                    onBannerClick = { banner ->
                        // Navigate to sale page
                    }
                )
            }
        }

        // Featured products - Section 1
        item(key = "FeaturedProductsSection1") {
            key(section1.hashCode()) {
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
        }

        // Featured products - Section 2
        item(key = "FeaturedProductsSection2") {
            key(section2.hashCode()) {
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
        }

        // Featured products - Section 3
        item(key = "FeaturedProductsSection3") {
            key(section3.hashCode()) {
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
        }

        // Featured products - Section 4
        item(key = "FeaturedProductsSection4") {
            key(section4.hashCode()) {
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
