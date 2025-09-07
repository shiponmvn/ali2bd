package com.aliexpressshoppingbd.ali2bd.presentation.search.screen

import ali2bd.composeapp.generated.resources.Res
import ali2bd.composeapp.generated.resources.default_image_loader
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import presentation.component.noRippleClickable
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.ConfigObject
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigItem
import com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.components.SearchBar
import com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.components.SearchHistoryItem
import com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.viewmodel.SearchViewModel

import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.jetbrains.compose.resources.painterResource
import presentation.theme.BackgroundContent

data class ShoppingPlatform(
    val name: String,
    val imageUrl: String? = null
)

data class CountryItem(
    val label: String,
    val link: String,
    val imageUrl: String
)

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onNavigateBack: () -> Unit,
    onSearchItemClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.loadSystemConfig()
    }






    Scaffold(
        modifier = Modifier.safeDrawingPadding(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFF5722)) // Orange background color matching the image
                    .padding(8.dp)
            ) {
                SearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = viewModel::updateSearchQuery,
                    onSearch = { query ->
                        if (query.isNotEmpty()) {
                            onSearchItemClick(query)
                        }
                    },
                    onBackClick = onNavigateBack,
                    onClearClick = { viewModel.updateSearchQuery("") }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search History Section
            if (uiState.recentSearches.isNotEmpty()) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Search History",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )

                        if (uiState.recentSearches.isNotEmpty()) {
                            Text(
                                text = "Clear",
                                color = Color(0xFFFF5722),
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                modifier = Modifier.clickable { viewModel.clearSearchHistory() }
                            )
                        }
                    }
                }
            }

            // Search History Items or Empty State
            if (uiState.recentSearches.isEmpty()) {

            } else {
                items(uiState.recentSearches) { search ->
                    SearchHistoryItem(
                        search = search,
                        onClick = { onSearchItemClick(search) },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                }
            }

            if (uiState.recentSearches.isNotEmpty()) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Visit the site directly to shop items",
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { /* Handle tutorial click */ }
                        ) {
                            Text(
                                text = "Watch Tutorial",
                                color = Color(0xFFFF5722),
                                fontSize = 14.sp
                            )

                            // Tutorial video icon placeholder
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(start = 4.dp)
                            ) {
                                Text(
                                    text = "▶",
                                    color = Color(0xFFFF5722),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            // Shopping Platforms Grid
            item {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp) // Adjust height to fit 2 rows of items
                ) {
                    items(uiState.countrySelection) { platform ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable {

                                }
                        ) {
                            // Placeholder image for platforms
                            Box(
                                modifier = Modifier.fillMaxWidth().fillMaxHeight(.8f)
                                    .clip(MaterialTheme.shapes.small)
                            ) {
                                AsyncImage(
                                    platform.image,
                                    null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop,
                                    error = painterResource(Res.drawable.default_image_loader),
                                    placeholder = painterResource(Res.drawable.default_image_loader),

                                )

                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            platform.label?.let {
                                Text(
                                    text = it,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }

        // Loading Indicator
        if (uiState.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(color = Color(0xFFFF5722))
            }
        }

    }
}
