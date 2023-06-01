package com.merttoptas.shoppingcompose.feature.appstate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.merttoptas.shoppingcompose.feature.category.navigation.navigateToCategory
import com.merttoptas.shoppingcompose.feature.home.navigation.HomeNavigationRoute
import com.merttoptas.shoppingcompose.feature.home.navigation.navigateToHome
import com.merttoptas.shoppingcompose.feature.navigation.TopLevelDestination
import com.merttoptas.shoppingcompose.feature.profile.navigation.navigateToProfile
import kotlinx.coroutines.CoroutineScope

/**
 * Created by mertcantoptas on 29.05.2023
 */

@Composable
fun rememberMainAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): MainAppState {
    return remember(navController, coroutineScope) {
        MainAppState(navController, coroutineScope)
    }
}

@Stable
class MainAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        @Composable get() = currentDestination?.hierarchy?.any { destination ->
            topLevelDestinations.any {
                destination.route?.contains(it.route) ?: false
            }
        } ?: false

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().toList()


    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelOptions = navOptions {
            popUpTo(HomeNavigationRoute) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToHome(topLevelOptions)
            TopLevelDestination.CATEGORY -> navController.navigateToCategory(topLevelOptions)
            TopLevelDestination.PROFILE -> navController.navigateToProfile(topLevelOptions)
        }
    }

    fun onBackPress() {
        navController.popBackStack()
    }

}