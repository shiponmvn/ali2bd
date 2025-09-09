package com.aliexpressshoppingbd.ali2bd.presentation.cart.presentation.viewmodel

import com.aliexpressshoppingbd.ali2bd.presentation.cart.data.res.CartResponse
import com.aliexpressshoppingbd.ali2bd.presentation.cart.domain.usecase.cart.CartUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartUseCase: CartUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        getCartItems()
    }

    fun getCartItems() {
        coroutineScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            cartUseCase.getCartItems().collect { result ->
                result.onSuccess { cartModel ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        cart = cartModel,
                        error = null
                    )
                }.onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Failed to load cart items"
                    )
                }
            }
        }
    }

    fun addToCart(productId: String, quantity: Int, attributes: Map<String, String>? = null) {
        coroutineScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            cartUseCase.addToCart(productId, quantity, attributes).collect { result ->
                result.onSuccess { cartModel ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        cart = cartModel,
                        error = null
                    )
                }.onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Failed to add item to cart"
                    )
                }
            }
        }
    }

    fun updateCartItem(cartItemId: String, quantity: Int) {
        coroutineScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            cartUseCase.updateCartItem(cartItemId, quantity).collect { result ->
                result.onSuccess { cartModel ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        cart = cartModel,
                        error = null
                    )
                }.onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Failed to update cart item"
                    )
                }
            }
        }
    }

    fun removeFromCart(cartItemId: String) {
        coroutineScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            cartUseCase.removeFromCart(cartItemId).collect { result ->
                result.onSuccess { cartModel ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        cart = cartModel,
                        error = null
                    )
                }.onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Failed to remove item from cart"
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun retry() {
        getCartItems()
    }
}

data class CartUiState(
    val isLoading: Boolean = false,
    val cart: CartResponse? = null,
    val error: String? = null
)
