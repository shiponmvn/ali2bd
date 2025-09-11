package com.aliexpressshoppingbd.ali2bd.presentation.categories.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryItem
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.ProductItem
import com.aliexpressshoppingbd.ali2bd.presentation.categories.domain.usecase.category.CategoryListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoryListUseCase: CategoryListUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {

    private val _uiState = MutableStateFlow(CategoriesUiState())
    val uiState: StateFlow<CategoriesUiState> = _uiState.asStateFlow()

    var selectedCategoryId: String? by mutableStateOf(null)
        private set

    init {
        loadCategories()
    }

    private fun loadCategories() {
        coroutineScope.launch {
            _uiState.value = _uiState.value.copy(isCategoriesLoading = true)

            try {
                val categoriesResult = categoryListUseCase.getCategories()
                categoriesResult.onSuccess { categories ->
                    _uiState.value = _uiState.value.copy(
                        categories = categories,
                        isCategoriesLoading = false,
                        categoriesError = null
                    )

                    // Select first category by default and load its products
                    if (categories.isNotEmpty()) {
                        selectCategory(categories.first().id)
                    }
                }.onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isCategoriesLoading = false,
                        categoriesError = error.message ?: "Unknown error occurred"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isCategoriesLoading = false,
                    categoriesError = e.message ?: "Unknown error occurred"
                )
            }
        }
    }

    fun selectCategory(categoryId: String) {
        selectedCategoryId = categoryId
        loadCategoryProducts(categoryId)
    }

    private fun loadCategoryProducts(categoryId: String) {
        coroutineScope.launch {
            _uiState.value = _uiState.value.copy(isProductsLoading = true)

            try {
                val productsResult = categoryListUseCase.getCategoryProducts(categoryId)
                productsResult.onSuccess { products ->
                    _uiState.value = _uiState.value.copy(
                        products = products,
                        isProductsLoading = false,
                        productsError = null
                    )
                }.onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isProductsLoading = false,
                        productsError = error.message ?: "Unknown error occurred"
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isProductsLoading = false,
                    productsError = e.message ?: "Unknown error occurred"
                )
            }
        }
    }

    fun retry() {
        loadCategories()
    }

    fun retryLoadingProducts() {
        selectedCategoryId?.let { loadCategoryProducts(it) }
    }
}

data class CategoriesUiState(
    val categories: List<CategoryItem> = emptyList(),
    val products: List<ProductItem> = emptyList(),
    val isCategoriesLoading: Boolean = false,
    val isProductsLoading: Boolean = false,
    val categoriesError: String? = null,
    val productsError: String? = null
)
