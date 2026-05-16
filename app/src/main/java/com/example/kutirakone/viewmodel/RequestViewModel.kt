package com.example.kutirakone.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kutirakone.models.Request
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RequestViewModel : ViewModel() {

    private val _requests = MutableStateFlow<List<Request>>(emptyList())
    val requests: StateFlow<List<Request>> = _requests

    fun createRequest(request: Request, onSuccess: () -> Unit) {
        onSuccess()
    }
}