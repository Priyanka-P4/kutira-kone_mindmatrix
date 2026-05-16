package com.example.kutirakone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kutirakone.models.Listing
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListingViewModel : ViewModel() {

    private val _listings = MutableStateFlow<List<Listing>>(emptyList())
    val listings: StateFlow<List<Listing>> = _listings

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun loadNearbyListings(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000)

            val mockListings = listOf(
                Listing(
                    id = "1",
                    materialType = "Silk",
                    sizeMetres = 0.5,
                    colour = "Red",
                    condition = "New",
                    listingType = "sell",
                    price = 150.0,
                    address = "Mehrauli Village, Delhi"
                ),
                Listing(
                    id = "2",
                    materialType = "Cotton",
                    sizeMetres = 1.0,
                    colour = "Blue",
                    condition = "Like New",
                    listingType = "swap",
                    swapOffer = "Looking for silk scraps",
                    address = "Chandni Chowk, Delhi"
                ),
                Listing(
                    id = "3",
                    materialType = "Wool",
                    sizeMetres = 0.3,
                    colour = "Green",
                    condition = "New",
                    listingType = "free",
                    address = "Hauz Rani, Delhi"
                )
            )

            _listings.value = mockListings
            _isLoading.value = false
        }
    }

    fun createListing(listing: Listing, onSuccess: () -> Unit) {
        onSuccess()
    }
}