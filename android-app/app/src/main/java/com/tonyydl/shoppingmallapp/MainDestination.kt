package com.tonyydl.shoppingmallapp

import androidx.navigation.NavType
import androidx.navigation.navArgument

object Login {
    const val route = "login"
}

object ProductList {
    const val route = "product_list"
}

object ProductDetails {
    const val route = "product_details"
    const val productIdArg = "product_id"
    const val productNameArg = "product_name"
    const val routeWithArg = "$route/{$productIdArg}/{$productNameArg}"
    val arguments = listOf(
        navArgument(productIdArg) { type = NavType.IntType },
        navArgument(productNameArg) { type = NavType.StringType }
    )
}