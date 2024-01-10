package com.tonyydl.shoppingmallapp.ui.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun SimpleImage(
    model: Any?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = model, contentDescription = null, modifier = modifier
    )
}