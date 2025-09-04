package com.aliexpressshoppingbd.ali2bd.di

import com.aliexpressshoppingbd.ali2bd.presentation.login.LoginViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.register.RegisterViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.viewmodel.SearchViewModel
import org.koin.dsl.module

val presentationModule = module {
    // ViewModels - Using factory for multiplatform compatibility
    factory { LoginViewModel(get()) }
    factory { RegisterViewModel(get()) }
    factory { SearchViewModel(get()) }
}

