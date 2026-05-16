package com.example.kutirakone.services

import com.example.kutirakone.models.DesignIdea

class MockAIService {

    fun generateDesignIdeas(materialType: String, sizeMetres: Double, colour: String): List<DesignIdea> {
        return listOf(
            DesignIdea(
                title = "Patchwork Cushion Cover",
                difficulty = "Easy",
                description = "Create a beautiful cushion cover using your fabric scrap.",
                materialsNeeded = listOf("Fabric scrap", "Needle & thread", "Cushion filling"),
                estimatedTime = "2 hours"
            ),
            DesignIdea(
                title = "Fabric Coasters",
                difficulty = "Easy",
                description = "Make 4 matching coasters for your home.",
                materialsNeeded = listOf("Fabric scrap", "Felt backing", "Glue"),
                estimatedTime = "1 hour"
            ),
            DesignIdea(
                title = "Drawstring Bag",
                difficulty = "Medium",
                description = "A reusable bag for gifts or shopping.",
                materialsNeeded = listOf("Fabric", "Ribbon", "Sewing machine"),
                estimatedTime = "1.5 hours"
            )
        )
    }
}