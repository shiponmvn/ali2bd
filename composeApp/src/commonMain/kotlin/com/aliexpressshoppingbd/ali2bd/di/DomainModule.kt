package com.aliexpressshoppingbd.ali2bd.di

import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.usecase.system_config.SystemConfigUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { SystemConfigUseCase(get()) }
}
