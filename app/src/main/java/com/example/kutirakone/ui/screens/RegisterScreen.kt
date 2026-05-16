package com.example.kutirakone.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kutirakone.models.User
import com.example.kutirakone.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    var name by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("Tailor") }
    var village by remember { mutableStateOf("") }
    var locality by remember { mutableStateOf("") }

    val roles = listOf("Tailor", "Artisan", "Both")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(text = "Create Your Profile", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Tell us about yourself", style = MaterialTheme.typography.bodyMedium)
        }

        item {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full Name *") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            var expanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                TextField(
                    value = role,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Role *") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    roles.forEach { selectedRole ->
                        DropdownMenuItem(
                            text = { Text(selectedRole) },
                            onClick = {
                                role = selectedRole
                                expanded = false
                            }
                        )
                    }
                }
            }
        }

        item {
            OutlinedTextField(
                value = village,
                onValueChange = { village = it },
                label = { Text("Village/Town *") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = locality,
                onValueChange = { locality = it },
                label = { Text("Locality/Area *") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Button(
                onClick = {
                    if (name.isNotBlank() && village.isNotBlank()) {
                        val user = User(
                            name = name,
                            phoneNumber = "+911234567890",
                            role = role,
                            village = village,
                            locality = locality,
                            createdAt = System.currentTimeMillis()
                        )
                        authViewModel.registerUser(
                            user,
                            onSuccess = { navController.navigate("home") },
                            onFailure = { }
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Complete Registration")
            }
        }
    }
}