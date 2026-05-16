package com.example.kutirakone.models

data class Listing(
    val id: String = "",
    val userId: String = "",
    val materialType: String = "",
    val sizeMetres: Double = 0.0,
    val colour: String = "",
    val condition: String = "",
    val listingType: String = "",
    val price: Double = 0.0,
    val swapOffer: String = "",
    val address: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis()
)