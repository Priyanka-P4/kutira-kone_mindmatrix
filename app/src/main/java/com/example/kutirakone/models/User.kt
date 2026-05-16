package com.example.kutirakone.models

data class User(
    val uid: String = "",
    val name: String = "",
    val phoneNumber: String = "",
    val role: String = "",
    val village: String = "",
    val locality: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val rating: Float = 0f,
    val listingCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)