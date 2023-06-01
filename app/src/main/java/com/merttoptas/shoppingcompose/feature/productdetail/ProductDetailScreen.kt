@file:OptIn(ExperimentalMaterial3Api::class)

package com.merttoptas.shoppingcompose.feature.productdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Created by mertcantoptas on 01.06.2023
 */

@Composable
internal fun ProductDetailRoute(
    modifier: Modifier = Modifier,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    val productUIState by viewModel.uiState.collectAsStateWithLifecycle()
    ProductDetailScreen(modifier = modifier, productUIState)
}

@Composable
fun ProductDetailScreen(modifier: Modifier = Modifier, productUIState: ProductDetailUiState) {
    Scaffold(modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = "Product Detail") })
        }) {
        Content(modifier = Modifier.padding(it), productUIState = productUIState)
    }
}

@Composable
private fun Content(modifier: Modifier, productUIState: ProductDetailUiState) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = productUIState.product?.title ?: "")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = productUIState.product?.category ?: "")
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = productUIState.product?.description ?: "")
    }


}