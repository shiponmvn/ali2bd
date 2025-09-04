package com.aliexpressshoppingbd.ali2bd.di

import com.aliexpressshoppingbd.ali2bd.domain.usecase.LoginUseCase
import com.aliexpressshoppingbd.ali2bd.domain.usecase.RegisterUseCase
import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.usecase.system_config.SystemConfigUseCase
import org.koin.dsl.module

val domainModule = module {
    // Use Cases
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { SystemConfigUseCase(get()) }
}
