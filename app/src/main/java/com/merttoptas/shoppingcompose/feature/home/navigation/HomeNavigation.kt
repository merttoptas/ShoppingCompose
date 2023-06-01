package com.merttoptas.shoppingcompose.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

/**
 * Created by mertcantoptas on 30.05.2023
 */
const val HomeNavigationRoute = "home_route"

fun NavController.navigateToHome(
    navOptions: NavOptions? = null,
) {
    this.navigate(HomeNavigationRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(navigateToDetail: (Int) -> Unit) {
    composable(HomeNavigationRoute) {
        HomeScreenRoute(navigateToDetail = navigateToDetail)
    }
}