package com.aliexpressshoppingbd.ali2bd.presentation.categories.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.aliexpressshoppingbd.ali2bd.presentation.categories.data.res.CategoryMenuItem
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

    var selectedCategoryMenu: CategoryMenuItem? by mutableStateOf(null)
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
                        selectCategory(categories.first())
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

    fun selectCategory(selectedCategory: CategoryMenuItem) {
        selectedCategoryMenu = selectedCategory;
        loadCategoryProducts(selectedCategory)
    }

    private fun loadCategoryProducts(selectedCategory: CategoryMenuItem) {
        coroutineScope.launch {

            _uiState.value = _uiState.value.copy(
                products = selectedCategory.children,
                isProductsLoading = false,
                productsError = null
            )

        }
    }

    fun retry() {
        loadCategories()
    }

    fun retryLoadingProducts() {
        selectedCategoryMenu?.let { loadCategoryProducts(it) }
    }
}

data class CategoriesUiState(
    val categories: List<CategoryMenuItem> = emptyList(),
    val products: List<CategoryMenuItem> = emptyList(),
    val isCategoriesLoading: Boolean = false,
    val isProductsLoading: Boolean = false,
    val categoriesError: String? = null,
    val productsError: String? = null
)
