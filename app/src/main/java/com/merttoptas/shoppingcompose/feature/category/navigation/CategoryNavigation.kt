package com.merttoptas.shoppingcompose.feature.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.merttoptas.shoppingcompose.feature.category.CategoryScreenRoute

/**
 * Created by mertcantoptas on 01.06.2023
 */

const val CategoryNavigationRoute = "category_route"

fun NavController.navigateToCategory(
    navOptions: NavOptions? = null,
) {
    this.navigate(CategoryNavigationRoute, navOptions)
}

fun NavGraphBuilder.categoryScreen() {
    composable(CategoryNavigationRoute) {
        CategoryScreenRoute()
    }
}