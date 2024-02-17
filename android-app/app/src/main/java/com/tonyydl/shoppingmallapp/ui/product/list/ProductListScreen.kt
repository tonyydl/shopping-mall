package com.tonyydl.shoppingmallapp.ui.product.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tonyydl.shoppingmallapp.R
import com.tonyydl.shoppingmallapp.data.vo.Product
import com.tonyydl.shoppingmallapp.ui.base.SimpleImage
import com.tonyydl.shoppingmallapp.ui.theme.ShoppingMallTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductListScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductListViewModel = hiltViewModel(),
    onProductClicked: (Product) -> Unit = { _ -> }
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val productList = uiState.productList
    
    val pullRefreshState = rememberPullRefreshState(uiState.isLoading, onRefresh = {
        viewModel.getProducts()
    })

    Box(Modifier.pullRefresh(pullRefreshState)) {
        val mediumPadding = dimensionResource(R.dimen.padding_medium)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(mediumPadding)
        ) {
            items(productList) { product ->
                ProductItem(
                    productName = product.productName,
                    productDescription = product.description,
                    productImageUrl = product.imageUrl
                ) {
                    onProductClicked(product)
                }
            }
        }

        PullRefreshIndicator(uiState.isLoading, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductItem(
    productName: String,
    productDescription: String,
    productImageUrl: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        onClick = onClick
    ) {
        Row(modifier = modifier.fillMaxWidth()) {
            SimpleImage(
                model = productImageUrl,
                modifier = Modifier
                    .width(100.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = productName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = productDescription,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductScreenPreview() {
    ShoppingMallTheme {
        ProductListScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    ProductItem(
        productName = "車子1",
        productDescription = "ford, mustang, car",
        productImageUrl = "https://cdn.pixabay.com/photo/2018/02/21/03/15/bmw-m4-3169357_1280.jpg"
    )
}