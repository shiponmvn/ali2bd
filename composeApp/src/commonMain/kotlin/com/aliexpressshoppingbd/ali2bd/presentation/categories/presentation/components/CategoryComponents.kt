package com.aliexpressshoppingbd.ali2bd.presentation.categories.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryItem
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.ProductItem

// Categories Sidebar Component
@Composable
fun CategoriesSidebar(
    categories: List<CategoryItem>,
    selectedCategoryId: String?,
    onCategoryClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .background(Color(0xFFF5F5F5))
    ) {
        items(categories) { category ->
            val isSelected = category.id == selectedCategoryId
            CategoryItem(
                name = category.name,
                isSelected = isSelected,
                onClick = { onCategoryClick(category.id) }
            )
        }
    }
}

@Composable
fun CategoryItem(
    name: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                if (isSelected) Color(0xFFFFEEEE) else Color.Transparent
            )
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 12.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) Color(0xFFE63946) else Color(0xFF333333)
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

// Products Grid Component
@Composable
fun CategoryProductsGrid(
    products: List<ProductItem>,
    onProductClick: (ProductItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(products) { product ->
            ProductGridItem(
                product = product,
                onClick = { onProductClick(product) }
            )
        }
    }
}

@Composable
fun ProductGridItem(
    product: ProductItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        // Product Image
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(2.dp, RoundedCornerShape(8.dp))
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            KamelImage(
                resource = asyncPainterResource(product.imageUrl),
                contentDescription = product.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                onLoading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFEEEEEE))
                    )
                },
                onFailure = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFEEEEEE)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("!")
                    }
                }
            )
        }

        // Product Title
        Text(
            text = product.title,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

// Loading, Error, and Empty State Components
@Composable
fun CategoriesLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun CategoriesError(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
fun CategoryProductsEmpty(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "No products found in this category",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}
