package com.aliexpressshoppingbd.ali2bd.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aliexpressshoppingbd.ali2bd.presentation.home.domain.model.HomeProductSectionModel
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.presentation.components.ProductListItem

data class Product(
    val id: String,
    val name: String,
    val currentPrice: String,
    val originalPrice: String? = null,
    val imageUrl: String? = null,
    val isWishlisted: Boolean = false
)

@Composable
fun FeaturedProductsSection(
    productSectionModel: HomeProductSectionModel?,
    onProductClick: (Product) -> Unit = {},
    onWishlistClick: (Product) -> Unit = {},
    onViewAllClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp)

    ) {
        // Section header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = productSectionModel?.keyword ?: "Featured Products",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.clickable { onViewAllClick() }
            ) {
                Text(
                    text = "View All",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF666666),
                    fontSize = 14.sp
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "View All Products",
                    tint = Color(0xFF666666),
                    modifier = Modifier.size(16.dp)
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier.fillMaxWidth().weight(0.1f),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val products = productSectionModel?.products?.take(6) ?: emptyList()

            items(
                items = products,
                key = { product -> product.vpid },
                contentType = { "product_item" }
            ) { product ->
                val onClick = remember(product.vpid) { { } }
                ProductListItem(
                    product = product,
                    onClick = onClick
                )
            }
        }


    }
}


