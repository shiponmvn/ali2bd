package com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigItem
import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.usecase.system_config.SystemConfigUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonPrimitive

class SearchViewModel(
    private val systemConfigUseCase: SystemConfigUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val defaultSearchSuggestions = listOf(
        "Smartphone",
        "Laptop",
        "Men's Clothing",
        "Women's Shoes",
        "Smart Watch",
        "Bluetooth Earbuds"
    )

    private val defaultRecentSearches = listOf(
        "Headphones",
        "iPhone case",
        "Gaming mouse",
        "Keyboard"
    )

    init {
        // Initialize with default values until API data is loaded
        _uiState.update { currentState ->
            currentState.copy(
                recentSearches = defaultRecentSearches,
                searchSuggestions = defaultSearchSuggestions,
                popularSearches = defaultSearchSuggestions
            )
        }
    }

    fun loadSystemConfig() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        coroutineScope.launch {
            try {
                systemConfigUseCase.invoke().collect { result ->
                    result.onSuccess { response ->
                        val popularSearches = extractPopularSearches(response.data)
                        val searchSuggestions = extractSearchSuggestions(response.data)

                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                popularSearches = popularSearches,
                                searchSuggestions = if (_uiState.value.searchQuery.isNotEmpty()) {
                                    filterSuggestions(_uiState.value.searchQuery, searchSuggestions)
                                } else {
                                    emptyList()
                                }
                            )
                        }
                    }.onFailure { error ->
                        _uiState.update { currentState ->
                            currentState.copy(
                                isLoading = false,
                                error = error.message ?: "Unknown error occurred"
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error occurred"
                    )
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.update { currentState ->
            currentState.copy(
                searchQuery = query,
                searchSuggestions = if (query.isNotEmpty()) {
                    filterSuggestions(query, currentState.popularSearches)
                } else {
                    emptyList()
                }
            )
        }
    }

    fun clearSearchHistory() {
        _uiState.update { currentState ->
            currentState.copy(recentSearches = emptyList())
        }
    }

    private fun filterSuggestions(query: String, suggestions: List<String>): List<String> {
        return suggestions.filter { it.contains(query, ignoreCase = true) }
    }

    private fun extractPopularSearches(configItems: List<SystemConfigItem>): List<String> {
        val popularSearchesItem = configItems.find { it.key == "popular_searches" }

        return try {
            popularSearchesItem?.value?.jsonArray?.mapNotNull {
                (it as? JsonPrimitive)?.content
            } ?: defaultSearchSuggestions
        } catch (e: Exception) {
            defaultSearchSuggestions
        }
    }

    private fun extractSearchSuggestions(configItems: List<SystemConfigItem>): List<String> {
        val suggestionsItem = configItems.find { it.key == "search_suggestions" }

        return try {
            suggestionsItem?.value?.jsonArray?.mapNotNull {
                (it as? JsonPrimitive)?.content
            } ?: defaultSearchSuggestions
        } catch (e: Exception) {
            defaultSearchSuggestions
        }
    }
}

data class SearchUiState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val recentSearches: List<String> = emptyList(),
    val popularSearches: List<String> = emptyList(),
    val searchSuggestions: List<String> = emptyList()
)
