package com.aliexpressshoppingbd.ali2bd.presentation.home.presentation.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Woman
import androidx.compose.ui.graphics.Color
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigItem
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.ValueData
import com.aliexpressshoppingbd.ali2bd.common.extentions.getValueAsList
import com.aliexpressshoppingbd.ali2bd.main.components.Category
import com.aliexpressshoppingbd.ali2bd.main.components.PromoBanner
import com.aliexpressshoppingbd.ali2bd.presentation.home.domain.model.HomeProductSectionModel
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.res.ProductItem
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.domain.usecase.product.ProductListUseCase
import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.model.SearchCustomModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.usecase.system_config.SystemConfigUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val systemConfigUseCase: SystemConfigUseCase,
    private val productListUseCase: ProductListUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _uiProductSectionUiState = MutableStateFlow(ProductSectionUiState())
    private val _uiCategoryState = MutableStateFlow(CategoriesUiState())
    private val _uiPromoBannerState = MutableStateFlow(BannerUiState())

    val uiCategoryState: StateFlow<CategoriesUiState> = _uiCategoryState.asStateFlow()
    val uiPromoBannerState: StateFlow<BannerUiState> = _uiPromoBannerState.asStateFlow()

    val uiProductSectionUiState: StateFlow<ProductSectionUiState> = _uiProductSectionUiState.asStateFlow()

    init {
        loadCategoryList()
        loadPromoBannerList()
        searchProducts("Bags");
    }


    fun searchProducts(keyword: String) {
        coroutineScope.launch {

            productListUseCase.searchProducts(keyword).collect { result ->
                result.onSuccess { products ->
                    _uiProductSectionUiState.value = _uiProductSectionUiState.value.copy(
                        section1 = HomeProductSectionModel(keyword, products.items),
                    )
                }.onFailure { exception ->
                    _uiProductSectionUiState.value = _uiProductSectionUiState.value.copy(
                        section1 = HomeProductSectionModel(keyword, emptyList()),
                    )
                }

            }
        }
    }





    fun loadCategoryList() {
        val categoryList = extractCategorySection()
        _uiCategoryState.update { currentState ->
            currentState.copy(categories = categoryList)
        }
    }

    fun loadPromoBannerList() {
        val bannerList = extractPromoBanners()
        _uiPromoBannerState.update { currentState ->
            currentState.copy(banners = bannerList)
        }
    }



    private fun filterSuggestions(query: String, suggestions: List<String>): List<String> {
        return suggestions.filter { it.contains(query, ignoreCase = true) }
    }


    private fun extractCountriesSection(configItems: List<SystemConfigItem>): List<ValueData> {
        val countriesItem = configItems.find { it.key == "countries_section" }
        val valueList = countriesItem?.rawValue?.getValueAsList().orEmpty()
        return valueList.filter { it.key == "country" }
    }

    private fun extractCategorySection(): List<Category> {
        val categories = listOf(
            Category("1", "Fashion", Icons.Default.ShoppingBag, Color(0xFF9C27B0)),
            Category("2", "Beauty", Icons.Default.Face, Color(0xFFE91E63)),
            Category("3", "Men's", Icons.Default.Person, Color(0xFF2196F3)),
            Category("4", "Women's", Icons.Default.Woman, Color(0xFF4CAF50)),
            Category("5", "Kids", Icons.Default.ChildCare, Color(0xFFFF9800))
        )
        return categories
    }

    private fun extractPromoBanners(): List<PromoBanner> {
        val banners = listOf(
            PromoBanner("https://static.vecteezy.com/system/resources/previews/006/532/742/non_2x/flash-sale-banner-illustration-template-design-of-special-offer-discount-for-media-promotion-and-social-media-post-free-vector.jpg")
        )
        return banners
    }


}

data class CategoriesUiState(
    val categories: List<Category> = emptyList(),
)

data class BannerUiState(
    val banners: List<PromoBanner> = emptyList(),
)
data class ProductSectionUiState(
    val section1: HomeProductSectionModel? = null,
    val section2: HomeProductSectionModel? = null,
    val section3: HomeProductSectionModel? = null,
    val section4: HomeProductSectionModel? = null,
)
