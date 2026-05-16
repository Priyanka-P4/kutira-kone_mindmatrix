package com.example.kutirakone.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kutirakone.services.MockAIService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InspireMeScreen() {
    var materialType by remember { mutableStateOf("") }
    var sizeMetres by remember { mutableStateOf("") }
    var colour by remember { mutableStateOf("") }
    var ideas by remember { mutableStateOf("") }

    val aiService = MockAIService()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text("✨ AI Design Studio", style = MaterialTheme.typography.headlineMedium)
            Text("Get creative ideas for your fabric scraps")
        }

        item {
            OutlinedTextField(
                value = materialType,
                onValueChange = { materialType = it },
                label = { Text("Material Type") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = sizeMetres,
                onValueChange = { sizeMetres = it },
                label = { Text("Size (metres)") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = colour,
                onValueChange = { colour = it },
                label = { Text("Colour") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            Button(
                onClick = {
                    val generatedIdeas = aiService.generateDesignIdeas(materialType, sizeMetres.toDoubleOrNull() ?: 0.0, colour)
                    ideas = generatedIdeas.joinToString("\n\n") { "${it.title}\n${it.description}\nMaterials: ${it.materialsNeeded.joinToString()}\nTime: ${it.estimatedTime}\n---" }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.AutoAwesome, contentDescription = "Generate")
                Text("Generate Ideas")
            }
        }

        if (ideas.isNotBlank()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Text(
                        text = ideas,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}