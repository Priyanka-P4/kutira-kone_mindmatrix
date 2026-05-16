package com.example.kutirakone.models

data class Request(
    val id: String = "",
    val listingId: String = "",
    val requesterId: String = "",
    val sellerId: String = "",
    val type: String = "",
    val swapOffer: String = "",
    val status: String = "pending",
    val createdAt: Long = System.currentTimeMillis()
)