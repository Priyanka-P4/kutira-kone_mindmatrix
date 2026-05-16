package com.example.kutirakone

import com.example.kutirakone.ui.screens.LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kutirakone.ui.screens.*
import com.example.kutirakone.ui.theme.KutiraKoneTheme
import com.example.kutirakone.viewmodel.AuthViewModel
import com.example.kutirakone.viewmodel.ListingViewModel
import com.example.kutirakone.viewmodel.RequestViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KutiraKoneTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") { SplashScreen(navController, authViewModel) }
        composable("login") { LoginScreen(navController, authViewModel) }
        composable("register") { RegisterScreen(navController, authViewModel) }
        composable("home") { HomeScreen(navController, authViewModel) }
        composable("listings") { ListingsScreen(navController) }
        composable("upload") { UploadListingScreen(navController) }
        composable("requests") { RequestsScreen(navController) }
        composable("profile") { ProfileScreen(navController, authViewModel) }
        composable("inspire_me") { InspireMeScreen() }
    }
}