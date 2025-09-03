package com.aliexpressshoppingbd.ali2bd.di

import com.aliexpressshoppingbd.ali2bd.data.network.AuthApi
import com.aliexpressshoppingbd.ali2bd.data.network.createHttpClient
import com.aliexpressshoppingbd.ali2bd.data.repository.AuthRepositoryImpl
import com.aliexpressshoppingbd.ali2bd.domain.repository.AuthRepository
import com.aliexpressshoppingbd.ali2bd.domain.usecase.LoginUseCase
import com.aliexpressshoppingbd.ali2bd.presentation.login.LoginViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun appModule(): Module = module {
    // Network
    single { createHttpClient() }
    single { AuthApi(get()) }

    // Repositories
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    // Use Cases
    factory { LoginUseCase(get()) }

    // ViewModels
    factory { LoginViewModel(get()) }
}
