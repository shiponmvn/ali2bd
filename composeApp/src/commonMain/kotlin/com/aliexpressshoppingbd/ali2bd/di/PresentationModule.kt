package com.aliexpressshoppingbd.ali2bd.di

import com.aliexpressshoppingbd.ali2bd.presentation.cart.presentation.viewmodel.CartViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.categories.presentation.viewmodel.CategoriesViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.home.presentation.viewmodel.HomeViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.productlist.presentation.viewmodel.ProductListViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.viewmodel.SearchViewModel
import org.koin.dsl.module

val presentationModule = module {
    single { SearchViewModel(get()) }
    factory { ProductListViewModel(get()) }
    single { CartViewModel(get()) }
    single { HomeViewModel(get(),get ()) }
    single { CategoriesViewModel(get()) }
}
