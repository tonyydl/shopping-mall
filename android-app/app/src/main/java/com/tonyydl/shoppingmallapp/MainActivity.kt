package com.tonyydl.shoppingmallapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tonyydl.shoppingmallapp.ui.login.LoginScreen
import com.tonyydl.shoppingmallapp.ui.product.ProductScreen
import com.tonyydl.shoppingmallapp.ui.theme.ShoppingMallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }

    @Composable
    private fun MainApp() {
        ShoppingMallTheme {
            val navController = rememberNavController()
            val context = LocalContext.current

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Login.route
                ) {
                    composable(route = Login.route) {
                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate(Product.route)
                            },
                            onLoginFailed = {
                                showToastMessage(it.asString(context))
                            }
                        )
                    }
                    composable(route = Product.route) {
                        ProductScreen()
                    }
                }
            }
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}