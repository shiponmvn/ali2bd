package com.aliexpressshoppingbd.ali2bd.presentation.productlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.data.res.ProductItem
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.domain.usecase.product.ProductListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val productListUseCase: ProductListUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)

) {

    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState: StateFlow<ProductListUiState> = _uiState.asStateFlow()

    fun searchProducts(keyword: String) {
        coroutineScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            productListUseCase.searchProducts(keyword).collect { result ->
               result.onSuccess { products ->
                   _uiState.value = _uiState.value.copy(
                       isLoading = false,
                       products = products.items,
                       error = null
                   )
               }.onFailure { exception ->
                   _uiState.value = _uiState.value.copy(
                       isLoading = false,
                       error = exception.message ?: "Unknown error occurred"
                   )
               }

            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun retry(keyword: String) {
        searchProducts(keyword)
    }
}

data class ProductListUiState(
    val isLoading: Boolean = false,
    val products: List<ProductItem> = emptyList(),
    val error: String? = null
)
