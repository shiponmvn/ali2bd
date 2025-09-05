package com.aliexpressshoppingbd.ali2bd.di

import org.koin.dsl.module

val appModule = module {
    includes(
        dataModule,
        domainModule,
        presentationModule
    )
}
