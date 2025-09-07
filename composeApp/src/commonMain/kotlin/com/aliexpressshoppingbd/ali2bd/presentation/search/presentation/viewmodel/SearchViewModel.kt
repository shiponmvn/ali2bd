package com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.viewmodel

import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.SystemConfigItem
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.res.ValueData
import com.aliexpressshoppingbd.ali2bd.common.extentions.getValueAsList
import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.model.SearchCustomModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.usecase.system_config.SystemConfigUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val systemConfigUseCase: SystemConfigUseCase,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    init {
        loadStoreList()
    }

    fun loadSystemConfig() {
        _uiState.update { it.copy(isLoading = true, error = null) }

        coroutineScope.launch {
            try {
                systemConfigUseCase.invoke().collect { result ->
                    result.onSuccess { response ->
                        val countries = extractCountriesSection(response.data)
                        _uiState.update { currentState ->
                            currentState.copy(
                                recentSearches = emptyList(),
                                searchSuggestions = emptyList(),
                                popularSearches = emptyList(),
                                isLoading = false,
                                countrySelection = countries,
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

    fun loadStoreList() {
        val storeList = SearchCustomModel.getStoreList()
        _uiState.update { currentState ->
            currentState.copy(storeList = storeList)
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


    private fun extractCountriesSection(configItems: List<SystemConfigItem>): List<ValueData> {
        val countriesItem = configItems.find { it.key == "countries_section" }
        val valueList = countriesItem?.rawValue?.getValueAsList().orEmpty()
        return valueList.filter { it.key == "country" }
    }



}


data class SearchUiState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val recentSearches: List<String> = emptyList(),
    val popularSearches: List<String> = emptyList(),
    val searchSuggestions: List<String> = emptyList(),
    val countrySelection: List<ValueData> = emptyList(),
    val storeList: List<SearchCustomModel> = emptyList()
)
