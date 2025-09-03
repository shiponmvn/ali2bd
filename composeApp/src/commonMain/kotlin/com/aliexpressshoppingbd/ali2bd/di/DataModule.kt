package com.aliexpressshoppingbd.ali2bd.di

import com.aliexpressshoppingbd.ali2bd.data.network.LoginApiService
import com.aliexpressshoppingbd.ali2bd.data.repository.UserRepositoryImpl
import com.aliexpressshoppingbd.ali2bd.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    // API Services
    single { LoginApiService(get()) }

    // Repositories
    single<UserRepository> { UserRepositoryImpl(get()) }
}
