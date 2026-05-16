package com.example.kutirakone.services

import android.content.Context
import com.example.kutirakone.models.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class FirebaseService(private val context: Context) {

    // Mock data storage
    private val _listings = MutableStateFlow<List<Listing>>(emptyList())
    val listings: StateFlow<List<Listing>> = _listings

    private val _requests = MutableStateFlow<List<Request>>(emptyList())
    val requests: StateFlow<List<Request>> = _requests

    private val _messages = MutableStateFlow<Map<String, List<Message>>>(emptyMap())

    init {
        // Load mock data
        loadMockData()
    }

    private fun loadMockData() {
        val mockListings = listOf(
            Listing(
                id = "1",
                userId = "user1",
                materialType = "Silk",
                sizeMetres = 0.5,
                colour = "Red",
                condition = "New",
                listingType = "sell",
                price = 150.0,
                latitude = 28.6139,
                longitude = 77.2090,
                address = "Mehrauli Village, New Delhi"
            ),
            Listing(
                id = "2",
                userId = "user2",
                materialType = "Cotton",
                sizeMetres = 1.0,
                colour = "Blue",
                condition = "Like New",
                listingType = "swap",
                swapOffer = "Looking for silk scraps",
                latitude = 28.6145,
                longitude = 77.2100,
                address = "Chandni Chowk, Delhi"
            ),
            Listing(
                id = "3",
                userId = "user1",
                materialType = "Wool",
                sizeMetres = 0.3,
                colour = "Green",
                condition = "New",
                listingType = "free",
                price = 0.0,
                latitude = 28.6130,
                longitude = 77.2085,
                address = "Hauz Rani, Delhi"
            )
        )
        _listings.value = mockListings
    }

    suspend fun sendOTP(phoneNumber: String): String {
        delay(1000) // Simulate network call
        return "mock_verification_id"
    }

    suspend fun verifyOTP(code: String): Boolean {
        delay(1000)
        return code == "123456" // Mock: any 6-digit code works
    }

    suspend fun createUser(user: User): Result<String> {
        delay(1000)
        return Result.success("mock_uid_${System.currentTimeMillis()}")
    }

    suspend fun getUser(uid: String): User? {
        delay(500)
        return User(
            uid = uid,
            name = "Test User",
            phoneNumber = "+911234567890",
            role = "Tailor",
            village = "Test Village",
            locality = "Test Locality",
            rating = 4.5f,
            listingCount = 3
        )
    }

    suspend fun createListing(listing: Listing, images: List<ByteArray>): Result<String> {
        delay(1500)
        val newListing = listing.copy(id = "listing_${System.currentTimeMillis()}")
        _listings.value = _listings.value + newListing
        return Result.success(newListing.id)
    }

    suspend fun getNearbyListings(
        latitude: Double,
        longitude: Double,
        radiusKm: Int,
        materialType: String? = null,
        colour: String? = null
    ): List<Listing> {
        delay(800)
        return _listings.value.filter { listing ->
            val distance = calculateDistance(
                latitude, longitude,
                listing.latitude, listing.longitude
            )
            distance <= radiusKm &&
                    (materialType == null || listing.materialType == materialType) &&
                    (colour == null || listing.colour == colour)
        }
    }

    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val R = 6371
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        return R * c
    }

    suspend fun createRequest(request: Request): Result<String> {
        delay(1000)
        val newRequest = request.copy(id = "req_${System.currentTimeMillis()}")
        _requests.value = _requests.value + newRequest
        return Result.success(newRequest.id)
    }

    suspend fun sendMessage(requestId: String, message: Message): Result<Unit> {
        delay(500)
        val currentMessages = _messages.value[requestId] ?: emptyList()
        _messages.value = _messages.value.toMutableMap().apply {
            this[requestId] = currentMessages + message
        }
        return Result.success(Unit)
    }

    suspend fun getMessages(requestId: String): List<Message> {
        delay(500)
        return _messages.value[requestId] ?: emptyList()
    }

    suspend fun updateRequestStatus(requestId: String, status: String): Result<Unit> {
        delay(500)
        val updatedRequests = _requests.value.map { request ->
            if (request.id == requestId) request.copy(status = status) else request
        }
        _requests.value = updatedRequests
        return Result.success(Unit)
    }

    fun getCurrentUserId(): String = "mock_user_123"
}