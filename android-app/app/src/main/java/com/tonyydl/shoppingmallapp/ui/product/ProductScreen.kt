package com.tonyydl.shoppingmallapp.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tonyydl.shoppingmallapp.R
import com.tonyydl.shoppingmallapp.ui.theme.ShoppingMallTheme

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    productViewModel: ProductViewModel = viewModel()
) {
    val uiState by productViewModel.uiState.collectAsStateWithLifecycle()

    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    Column(
        modifier = modifier.padding(mediumPadding)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
private fun ProductScreenPreview() {
    ShoppingMallTheme {
        ProductScreen()
    }
}