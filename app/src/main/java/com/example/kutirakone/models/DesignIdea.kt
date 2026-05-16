package com.example.kutirakone.models

data class DesignIdea(
    val title: String,
    val difficulty: String,
    val description: String,
    val materialsNeeded: List<String>,
    val estimatedTime: String
)