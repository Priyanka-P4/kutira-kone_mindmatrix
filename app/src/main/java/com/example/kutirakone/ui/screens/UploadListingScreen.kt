package com.example.kutirakone.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.kutirakone.models.Listing
import com.example.kutirakone.viewmodel.ListingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UploadListingScreen(navController: NavController) {
    val listingViewModel: ListingViewModel = viewModel()

    var selectedImageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var materialType by remember { mutableStateOf("") }
    var sizeMetres by remember { mutableStateOf("") }
    var colour by remember { mutableStateOf("") }
    var listingType by remember { mutableStateOf("Sell") }
    var price by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val materialTypes = listOf("Silk", "Cotton", "Wool", "Synthetic", "Blend")
    val listingTypes = listOf("Sell", "Swap", "Free")

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri>? ->
        uris?.let { selectedImageUris = it.take(5) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sell Your Fabric Scrap") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
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
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("📸 Photos (Required)", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedButton(
                                onClick = { imagePickerLauncher.launch("image/*") },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.PhotoLibrary, contentDescription = "Gallery")
                                Text("Gallery")
                            }
                        }

                        if (selectedImageUris.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("${selectedImageUris.size} photo(s) selected")
                        }
                    }
                }
            }

            item {
                var expanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                    TextField(
                        value = materialType,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Material Type *") },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        materialTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type) },
                                onClick = { materialType = type; expanded = false }
                            )
                        }
                    }
                }
            }

            item {
                OutlinedTextField(
                    value = sizeMetres,
                    onValueChange = { sizeMetres = it },
                    label = { Text("Size (in metres) *") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                OutlinedTextField(
                    value = colour,
                    onValueChange = { colour = it },
                    label = { Text("Colour *") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                var expanded by remember { mutableStateOf(false) }
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = it }) {
                    TextField(
                        value = listingType,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("I want to *") },
                        modifier = Modifier.menuAnchor().fillMaxWidth()
                    )
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        listingTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type) },
                                onClick = { listingType = type; expanded = false }
                            )
                        }
                    }
                }
            }

            if (listingType == "Sell") {
                item {
                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        label = { Text("Price (₹) *") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            item {
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Your Location *") },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Button(
                    onClick = {
                        if (selectedImageUris.isNotEmpty() && materialType.isNotBlank() && sizeMetres.isNotBlank()) {
                            isLoading = true
                            val listing = Listing(
                                materialType = materialType,
                                sizeMetres = sizeMetres.toDoubleOrNull() ?: 0.0,
                                colour = colour,
                                listingType = listingType.lowercase(),
                                price = price.toDoubleOrNull() ?: 0.0,
                                address = address
                            )
                            listingViewModel.createListing(listing) {
                                isLoading = false
                                navController.navigate("home")
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !isLoading
                ) {
                    if (isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp))
                    else Text("Publish Listing")
                }
            }
        }
    }
}