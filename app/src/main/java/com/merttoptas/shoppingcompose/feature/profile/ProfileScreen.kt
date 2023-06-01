package com.merttoptas.shoppingcompose.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Created by mertcantoptas on 01.06.2023
 */

@Composable
internal fun ProfileScreenRoute(
    modifier: Modifier = Modifier,
) {
    ProfileScreenScreen(modifier = modifier)
}

@Composable
fun ProfileScreenScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Profile Screen")
    }
}