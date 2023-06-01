package com.merttoptas.shoppingcompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.merttoptas.shoppingcompose.core.data.model.ProductResponse

/**
 * Created by mertcantoptas on 01.06.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    productItem: ProductResponse,
    onProductDetailClicked: (Int) -> Unit
) {
    Card(
        onClick = { onProductDetailClicked.invoke(productItem.id ?: 0) })
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = productItem.title ?: "")
            SuggestionChip(onClick = { /*TODO*/ },
                label = { Text(text = productItem.category ?: "") })
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "$${productItem.price}")
        }
    }
}