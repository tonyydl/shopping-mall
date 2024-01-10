package com.tonyydl.shoppingmallapp.ui.product.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tonyydl.shoppingmallapp.R
import com.tonyydl.shoppingmallapp.data.ProductCategory
import com.tonyydl.shoppingmallapp.ui.base.SimpleImage
import com.tonyydl.shoppingmallapp.utils.StringValue

@Composable
fun ProductDetailsScreen(
    productId: Int,
    productName: String,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = viewModel(),
    onGetProductFailed: (StringValue) -> Unit = { _ -> }
) {
    LaunchedEffect(Unit) {
        viewModel.getProductById(productId)
        viewModel.uiEvent.collect { event ->
            when (event) {
                ProductDetailsUiEvent.GetProductFailed ->
                    onGetProductFailed(
                        StringValue.StringResource(
                            R.string.get_product_details_failed,
                            productName
                        )
                    )
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val product = uiState.product

    Box(modifier = modifier) {
        product?.let {
            ProductDetailsLayout(
                productId = product.productId,
                productName = product.productName,
                category = product.category,
                imageUrl = product.imageUrl,
                price = product.price,
                stock = product.stock,
                description = product.description
            )
        } ?: ProductDetailsEmptyLayout()
    }
}

@Composable
fun ProductDetailsLayout(
    productId: Int,
    productName: String,
    category: ProductCategory,
    imageUrl: String,
    price: Int,
    stock: Int,
    description: String,
    modifier: Modifier = Modifier
) {
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    Column(
        modifier = modifier.padding(mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Text(
                text = productId.toString(),
                style = typography.headlineLarge,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = productName,
                style = typography.headlineLarge
            )
        }
        SimpleImage(
            model = imageUrl,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(R.string.product_details_price, price),
            style = typography.bodyLarge
        )
        Text(
            text = stringResource(R.string.product_details_stock, stock),
            style = typography.bodyLarge
        )
        Text(
            text = description,
            style = typography.bodyLarge
        )
    }
}

@Composable
fun ProductDetailsEmptyLayout(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.empty_box),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailsLayoutPreview() {
    ProductDetailsLayout(
        productId = 1,
        productName = "product 1",
        category = ProductCategory.CAR,
        imageUrl = "https://plus.unsplash.com/premium_photo-1669324357471-e33e71e3f3d8?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        price = 500,
        stock = 10,
        description = "description"
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailsEmptyLayoutPreview() {
    ProductDetailsEmptyLayout()
}