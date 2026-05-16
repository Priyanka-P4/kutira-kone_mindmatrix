package com.example.kutirakone.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kutirakone.viewmodel.AuthViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    LaunchedEffect(Unit) {
        delay(2000)
        if (authViewModel.isAuthenticated.value) {
            navController.navigate("home") {
                popUpTo("splash") { inclusive = true }
            }
        } else {
            navController.navigate("login") {
                popUpTo("splash") { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(text = "🧵", style = MaterialTheme.typography.displayLarge)
            Text(text = "Kutira-Kone", style = MaterialTheme.typography.headlineLarge)
            Text(text = "Cut & Connect in Spirit", style = MaterialTheme.typography.bodyMedium)
            CircularProgressIndicator()
        }
    }
}