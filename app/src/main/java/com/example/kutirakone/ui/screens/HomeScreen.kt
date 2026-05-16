package com.example.kutirakone.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kutirakone.viewmodel.AuthViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val currentUser by authViewModel.currentUser.collectAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Search, contentDescription = "Browse") },
                    label = { Text("Browse") },
                    selected = false,
                    onClick = { navController.navigate("listings") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Add, contentDescription = "Sell") },
                    label = { Text("Sell") },
                    selected = false,
                    onClick = { navController.navigate("upload") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Notifications, contentDescription = "Requests") },
                    label = { Text("Requests") },
                    selected = false,
                    onClick = { navController.navigate("requests") }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    selected = false,
                    onClick = { navController.navigate("profile") }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "Welcome back, ${currentUser?.name ?: "Artisan"}!",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🌱 Did you know?", style = MaterialTheme.typography.titleLarge)
                        Text("Every fabric scrap you sell reduces landfill waste!")
                    }
                }
            }

            item {
                Text("Quick Actions", style = MaterialTheme.typography.titleLarge)
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { navController.navigate("upload") },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Sell Scrap")
                    }
                    Button(
                        onClick = { navController.navigate("inspire_me") },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Get Ideas")
                    }
                }
            }
        }
    }
}