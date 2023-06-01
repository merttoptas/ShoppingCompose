package com.merttoptas.shoppingcompose.feature.productdetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.merttoptas.shoppingcompose.feature.productdetail.ProductDetailRoute

/**
 * Created by mertcantoptas on 01.06.2023
 */
const val ProductDetailNavigationRoute = "product_detail_route"

fun NavController.navigateToProductDetail(
    productId: Int,
    navOptions: NavOptions? = null,
) {
    this.navigate("$ProductDetailNavigationRoute/$productId", navOptions)
}

fun NavGraphBuilder.productDetailScreen() {
    composable(
        "$ProductDetailNavigationRoute/{productId}",
        arguments = listOf(
            navArgument("productId") {
                type = NavType.IntType
            }
        )
    ) {
        ProductDetailRoute()
    }
}