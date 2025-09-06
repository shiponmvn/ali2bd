package com.aliexpressshoppingbd.ali2bd.di

import business.core.KtorHttpClient
import business.datasource.network.main.SearchApiServiceImpl
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.api_service.SearchApiService
import org.koin.dsl.module

val dataModule = module {
    single<SearchApiService> { SearchApiServiceImpl(get()) }
    single {
        KtorHttpClient.httpClient()
    }
}
    // Repositories }
