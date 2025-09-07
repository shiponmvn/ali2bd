package com.aliexpressshoppingbd.ali2bd.di

import com.aliexpressshoppingbd.ali2bd.presentation.productlist.presentation.viewmodel.ProductListViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.viewmodel.SearchViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { SearchViewModel(get()) }
    factory { ProductListViewModel(get()) }
}

