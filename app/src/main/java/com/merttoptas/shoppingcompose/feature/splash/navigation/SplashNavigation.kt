package com.merttoptas.shoppingcompose.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.merttoptas.shoppingcompose.feature.splash.SplashScreenRoute

/**
 * Created by mertcantoptas on 30.05.2023
 */
const val SplashNavigationRoute = "splash_route"

fun NavGraphBuilder.splashScreen(navigateToHome: () -> Unit, navigateToLogin: () -> Unit) {
    composable(SplashNavigationRoute) {
        SplashScreenRoute(navigateToHome = navigateToHome, navigateToLogin = navigateToLogin)
    }
}