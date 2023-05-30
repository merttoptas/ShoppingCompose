package com.merttoptas.shoppingcompose.feature.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.merttoptas.shoppingcompose.feature.home.navigation.HomeNavigationRoute
import com.merttoptas.shoppingcompose.feature.home.navigation.homeScreen
import com.merttoptas.shoppingcompose.feature.login.navigation.LoginNavigationRoute
import com.merttoptas.shoppingcompose.feature.login.navigation.loginScreen
import com.merttoptas.shoppingcompose.feature.splash.navigation.SplashNavigationRoute
import com.merttoptas.shoppingcompose.feature.splash.navigation.splashScreen

/**
 * Created by mertcantoptas on 29.05.2023
 */

@Composable
fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = SplashNavigationRoute
) {
    NavHost(
        modifier = modifier,
        navController = navController, startDestination = startDestination
    ) {
        loginScreen()
        homeScreen()
        splashScreen(navigateToLogin = {
            navController.navigate(LoginNavigationRoute) {
                popUpTo(LoginNavigationRoute) {
                    inclusive = true
                }
            }
        }, navigateToHome = {
            navController.navigate(HomeNavigationRoute) {
                popUpTo(LoginNavigationRoute) {
                    inclusive = true
                }
            }
        })
    }
}