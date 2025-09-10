package com.aliexpressshoppingbd.ali2bd.presentation.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Woman
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aliexpressshoppingbd.ali2bd.main.components.CategoriesSection
import com.aliexpressshoppingbd.ali2bd.main.components.Category
import com.aliexpressshoppingbd.ali2bd.main.components.FeaturedProductsSection
import com.aliexpressshoppingbd.ali2bd.main.components.HomeHeader
import com.aliexpressshoppingbd.ali2bd.main.components.HomeSearchSection
import com.aliexpressshoppingbd.ali2bd.main.components.Product
import com.aliexpressshoppingbd.ali2bd.main.components.PromoBanner
import com.aliexpressshoppingbd.ali2bd.main.components.PromotionalBannerSection
import com.aliexpressshoppingbd.ali2bd.main.navigation.MainNavigationDestinations
import androidx.navigation.NavHostController
import com.aliexpressshoppingbd.ali2bd.presentation.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigateToSearch: () -> Unit
) {
    var cartItemCount by remember { mutableStateOf(3) }
    val uiProductSectionUiState by viewModel.uiProductSectionUiState.collectAsStateWithLifecycle()





    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            HomeHeader(
                userName = "User",
                cartItemCount = cartItemCount,
                onCartClick = {
                    // Navigate to cart
                }
            )
        }

        item {
            HomeSearchSection(
                onSearchClick = {
                    navigateToSearch();
                    // Navigate to Search screen
                },
                onVoiceClick = {
                    // Handle voice search
                },
                onMenuClick = {
                    // Handle menu click
                }
            )
        }

        item {
            CategoriesSection(
                categories = viewModel.uiCategoryState.value.categories,
                onCategoryClick = { category ->
                    // Navigate to category page
                },
                onViewAllClick = {
                    // Navigate to all categories
                }
            )
        }

        item {
            PromotionalBannerSection(
                banners = viewModel.uiPromoBannerState.value.banners,
                onBannerClick = { banner ->
                    // Navigate to sale page
                }
            )
        }

        item {
            FeaturedProductsSection(
                productSectionModel = uiProductSectionUiState.section1,
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

        item {
            FeaturedProductsSection(
                productSectionModel = uiProductSectionUiState.section2,
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

        item {
            FeaturedProductsSection(
                productSectionModel = uiProductSectionUiState.section3,
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

        item {
            FeaturedProductsSection(
                productSectionModel = uiProductSectionUiState.section4,
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
