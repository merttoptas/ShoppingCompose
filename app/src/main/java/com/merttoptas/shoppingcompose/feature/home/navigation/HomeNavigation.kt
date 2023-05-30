package com.merttoptas.shoppingcompose.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Created by mertcantoptas on 30.05.2023
 */
const val HomeNavigationRoute = "home_route"

fun NavGraphBuilder.homeScreen() {
    composable(HomeNavigationRoute) {
        HomeScreenRoute()
    }
}