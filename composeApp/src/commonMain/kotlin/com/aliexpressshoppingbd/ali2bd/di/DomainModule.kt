package com.aliexpressshoppingbd.ali2bd.di

import com.aliexpressshoppingbd.ali2bd.presentation.cart.domain.usecase.cart.CartUseCase
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.domain.usecase.product.ProductListUseCase
import com.aliexpressshoppingbd.ali2bd.presentation.search.domain.usecase.system_config.SystemConfigUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { SystemConfigUseCase(get()) }
    factory { ProductListUseCase(get()) }
    factory { CartUseCase(get()) }
}
