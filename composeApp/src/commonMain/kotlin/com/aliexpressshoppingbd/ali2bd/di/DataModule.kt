package com.aliexpressshoppingbd.ali2bd.di

import com.aliexpressshoppingbd.ali2bd.data.network.LoginApiService
import com.aliexpressshoppingbd.ali2bd.data.repository.UserRepositoryImpl
import com.aliexpressshoppingbd.ali2bd.domain.repository.UserRepository
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.api_service.SearchApiService
import com.aliexpressshoppingbd.ali2bd.presentation.search.data.repository.SearchRepositoryImpl
import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.repository.SearchRepository
import org.koin.dsl.module

val dataModule = module {
    // API Services
    single { LoginApiService(get()) }
    single { SearchApiService(get()) }

    // Repositories
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get<SearchApiService>()) }
}
