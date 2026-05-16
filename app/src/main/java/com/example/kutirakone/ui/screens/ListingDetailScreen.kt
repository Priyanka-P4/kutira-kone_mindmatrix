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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kutirakone.models.Listing
import com.example.kutirakone.services.MockAIService
import com.example.kutirakone.viewmodel.ListingViewModel
import com.example.kutirakone.viewmodel.RequestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingDetailScreen(
    navController: NavController,
    listingId: String?,
    listingViewModel: ListingViewModel = viewModel(),
    requestViewModel: RequestViewModel = viewModel()
) {
    val listings by listingViewModel.listings.collectAsState()
    val listing = listings.find { it.id == listingId }
    val aiService = MockAIService()

    var aiIdeas by remember { mutableStateOf("") }
    var isLoadingIdeas by remember { mutableStateOf(false) }

    LaunchedEffect(listing) {
        if (listing != null) {
            isLoadingIdeas = true
            val ideas = aiService.generateDesignIdeas(
                listing.materialType,
                listing.sizeMetres,
                listing.colour
            )
            aiIdeas = ideas.joinToString("\n\n") {
                "${it.title}\nDifficulty: ${it.difficulty}\n${it.description}\nTime: ${it.estimatedTime}"
            }
            isLoadingIdeas = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fabric Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        listing?.let { currentListing ->
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
                            Text(currentListing.materialType, style = MaterialTheme.typography.headlineSmall)
                            Text("Size: ${currentListing.sizeMetres}m", style = MaterialTheme.typography.bodyLarge)
                            Text("Colour: ${currentListing.colour}", style = MaterialTheme.typography.bodyLarge)
                            Text("Condition: ${currentListing.condition}", style = MaterialTheme.typography.bodyLarge)
                            if (currentListing.listingType == "sell") {
                                Text("Price: ₹${currentListing.price}", style = MaterialTheme.typography.titleLarge)
                            } else if (currentListing.listingType == "swap") {
                                Text("Looking for: ${currentListing.swapOffer}", style = MaterialTheme.typography.bodyMedium)
                            } else {
                                Text("FREE", style = MaterialTheme.typography.titleLarge)
                            }
                        }
                    }
                }

                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("✨ AI Design Ideas", style = MaterialTheme.typography.titleLarge)
                                Icon(Icons.Default.AutoAwesome, contentDescription = "AI")
                            }
                            Spacer(modifier = Modifier.height(8.dp))

                            if (isLoadingIdeas) {
                                CircularProgressIndicator()
                            } else if (aiIdeas.isNotBlank()) {
                                Text(aiIdeas, style = MaterialTheme.typography.bodyMedium)
                            } else {
                                Text("Click Inspire Me for ideas", style = MaterialTheme.typography.bodyMedium)
                            }

                            Text(
                                text = "🤖 AI-generated suggestions",
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }

                item {
                    Button(
                        onClick = { navController.navigate("inspire_me") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Get More Ideas")
                    }
                }
            }
        }
    }
}