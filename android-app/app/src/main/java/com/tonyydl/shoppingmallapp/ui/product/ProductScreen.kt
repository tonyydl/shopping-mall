package com.tonyydl.shoppingmallapp.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tonyydl.shoppingmallapp.R
import com.tonyydl.shoppingmallapp.data.dto.ProductCategory
import com.tonyydl.shoppingmallapp.data.dto.ProductDTO
import com.tonyydl.shoppingmallapp.ui.theme.ShoppingMallTheme
import java.util.Date

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel = viewModel()
) {
    val uiState by productViewModel.uiState.collectAsStateWithLifecycle()
    val productList = uiState.productList
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(mediumPadding)
    ) {
        items(productList) { productDto ->
            ProductItem(productDto)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductItem(
    productDto: ProductDTO,
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
            AsyncImage(
                model = productDto.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = productDto.productName.orEmpty(),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = productDto.description.orEmpty(),
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
        ProductScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    ProductItem(
        productDto = ProductDTO(
            productId = 1,
            productName = "車子1",
            category = ProductCategory.CAR,
            imageUrl = "https://cdn.pixabay.com/photo/2018/02/21/03/15/bmw-m4-3169357_1280.jpg",
            price = 500000,
            stock = 1,
            description = "ford, mustang, car",
            createdDate = 0L,
            lastModifiedDate = 0L
        )
    )
}