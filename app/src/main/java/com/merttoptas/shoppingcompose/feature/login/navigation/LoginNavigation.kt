package com.merttoptas.shoppingcompose.feature.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.merttoptas.shoppingcompose.feature.login.LoginScreenRoute

/**
 * Created by mertcantoptas on 29.05.2023
 */


const val LoginNavigationRoute = "login_route"

fun NavGraphBuilder.loginScreen() {
    composable(LoginNavigationRoute) {
        LoginScreenRoute()
    }
}