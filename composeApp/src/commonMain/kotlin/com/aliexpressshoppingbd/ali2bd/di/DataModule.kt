package com.aliexpressshoppingbd.ali2bd.di

import business.datasource.network.main.SearchApiServiceImpl
import com.aliexpressshoppingbd.ali2bd.data.network.KtorHttpClient
import com.aliexpressshoppingbd.ali2bd.data.network.LoginApiService
import com.aliexpressshoppingbd.ali2bd.data.repository.UserRepositoryImpl
import com.aliexpressshoppingbd.ali2bd.domain.repository.UserRepository
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.api_service.SearchApiService
import io.ktor.client.HttpClient
import org.koin.dsl.module

val dataModule = module {
    // API Services
    single { LoginApiService(get()) }
    single<SearchApiService> { SearchApiServiceImpl(get()) }
    single {
        KtorHttpClient.httpClient()
    }

    // Repositories
    single<UserRepository> { UserRepositoryImpl(get()) }
}
