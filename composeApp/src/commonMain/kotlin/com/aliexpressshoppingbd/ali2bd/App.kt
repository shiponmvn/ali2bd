package com.aliexpressshoppingbd.ali2bd


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.fetch.NetworkFetcher
import com.aliexpressshoppingbd.ali2bd.di.appModule
import com.aliexpressshoppingbd.ali2bd.navigation.AppNavigation
import com.aliexpressshoppingbd.ali2bd.main.MainScreen
import com.aliexpressshoppingbd.ali2bd.main.navigation.MainNavigationDestinations
import com.aliexpressshoppingbd.ali2bd.presentation.search.presentation.viewmodel.SearchViewModel
import com.aliexpressshoppingbd.ali2bd.presentation.search.screen.SearchScreen
import com.aliexpressshoppingbd.ali2bd.presentation.splash.SplashScren
import org.koin.compose.KoinApplication
import org.koin.compose.koinInject
import presentation.theme.AppTheme

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule)
    }) {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(NetworkFetcher.Factory())
                }
                .build()
        }

        AppTheme {
            val navigator = rememberNavController()



            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    navController = navigator,
                    startDestination = AppNavigation.Splash,
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable<AppNavigation.Splash> {
                        SplashScren (navigateToMain = {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Main)
                        })
                    }
                    composable<AppNavigation.Main> {
                        MainScreen {
                            navigator.popBackStack()
                            navigator.navigate(AppNavigation.Splash)
                        }
                    }
                    composable<AppNavigation.Search> {
                        val searchViewModel: SearchViewModel = koinInject()
                        SearchScreen(

                        )
                    }
                }
            }

        }
    }
}
