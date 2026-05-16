package com.example.kutirakone.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kutirakone.models.Listing
import com.example.kutirakone.viewmodel.ListingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingsScreen(
    navController: NavController,
    listingViewModel: ListingViewModel = viewModel()
) {
    val listings by listingViewModel.listings.collectAsState()
    val isLoading by listingViewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        listingViewModel.loadNearbyListings(28.6139, 77.2090)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Browse Fabric Scraps") }) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else if (listings.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.Search, contentDescription = "No results", modifier = Modifier.size(64.dp))
                    Text("No fabric scraps found nearby")
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(listings) { listing ->
                        ListingCard(listing = listing, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun ListingCard(listing: Listing, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        when (listing.materialType) {
                            "Silk" -> Color(0xFFFFD1DC)
                            "Cotton" -> Color(0xFFB3E5FC)
                            else -> Color(0xFFC8E6C9)
                        },
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Image, contentDescription = "Fabric", modifier = Modifier.size(48.dp))
            }

            Text(text = listing.materialType, style = MaterialTheme.typography.titleMedium)
            Text(text = "${listing.sizeMetres}m • ${listing.colour}", style = MaterialTheme.typography.bodySmall)

            when (listing.listingType) {
                "sell" -> Text(text = "₹${listing.price}", style = MaterialTheme.typography.titleSmall)
                "swap" -> Text(text = "Swap Only", style = MaterialTheme.typography.bodySmall)
                else -> Text(text = "FREE", style = MaterialTheme.typography.bodySmall)
            }

            Text(text = "📍 ${listing.address.take(20)}", style = MaterialTheme.typography.labelSmall)
        }
    }
}