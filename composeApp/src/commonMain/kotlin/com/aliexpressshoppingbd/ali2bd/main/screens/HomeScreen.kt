package com.aliexpressshoppingbd.ali2bd.main.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.aliexpressshoppingbd.ali2bd.main.components.CategoriesSection
import com.aliexpressshoppingbd.ali2bd.main.components.Category
import com.aliexpressshoppingbd.ali2bd.main.components.FeaturedProductsSection
import com.aliexpressshoppingbd.ali2bd.main.components.HomeHeader
import com.aliexpressshoppingbd.ali2bd.main.components.HomeSearchSection
import com.aliexpressshoppingbd.ali2bd.main.components.Product
import com.aliexpressshoppingbd.ali2bd.main.components.PromoBanner
import com.aliexpressshoppingbd.ali2bd.main.components.PromotionalBannerSection

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    var cartItemCount by remember { mutableStateOf(3) }

    // Sample data
    val categories = listOf(
        Category("1", "Fashion", Icons.Default.ShoppingBag, Color(0xFF9C27B0)),
        Category("2", "Beauty", Icons.Default.Face, Color(0xFFE91E63)),
        Category("3", "Men's", Icons.Default.Person, Color(0xFF2196F3)),
        Category("4", "Women's", Icons.Default.Woman, Color(0xFF4CAF50)),
        Category("5", "Kids", Icons.Default.ChildCare, Color(0xFFFF9800))
    )

    val promoBanners = listOf(
        PromoBanner("1", "Big Sale", "Up to 50% Discount")
    )

    val featuredProducts = listOf(
        Product("1", "White Jumpsuit", "₹1,100", "₹2,500"),
        Product("2", "Vitamin C Serum", "₹2,453", "₹4,000"),
        Product("3", "Strip T-shirt", "₹1,700", null)
    )

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
                    // Handle search click
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
                categories = categories,
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
                banners = promoBanners,
                onBannerClick = { banner ->
                    // Navigate to sale page
                }
            )
        }

        item {
            FeaturedProductsSection(
                products = featuredProducts,
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
