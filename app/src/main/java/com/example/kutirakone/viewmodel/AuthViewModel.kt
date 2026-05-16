package com.example.kutirakone.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kutirakone.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser

    fun sendOTP(phoneNumber: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        if (phoneNumber.isNotBlank()) onSuccess()
        else onFailure("Invalid number")
    }

    fun verifyOTP(code: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        if (code.length == 6) onSuccess()
        else onFailure("Invalid OTP")
    }

    fun registerUser(user: User, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        _currentUser.value = user
        _isAuthenticated.value = true
        onSuccess()
    }

    fun logout() {
        _isAuthenticated.value = false
        _currentUser.value = null
    }
}