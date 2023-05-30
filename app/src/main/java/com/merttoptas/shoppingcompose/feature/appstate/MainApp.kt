@file:OptIn(ExperimentalMaterial3Api::class)

package com.merttoptas.shoppingcompose.feature.appstate

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.merttoptas.shoppingcompose.feature.navigation.MainNavHost

/**
 * Created by mertcantoptas on 29.05.2023
 */

@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    appState: MainAppState = rememberMainAppState(),
) {
    Scaffold(modifier = modifier) {
        MainNavHost(
            navController = appState.navController,
            modifier = modifier.padding(it)
        )
    }
}