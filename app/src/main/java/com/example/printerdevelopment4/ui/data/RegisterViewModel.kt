package com.example.printerdevelopment4.ui.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState(null, null))
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    var EnteredPassword by mutableStateOf("")
    var EnteredEmail by mutableStateOf("")
    var EnteredSecPass by mutableStateOf("")

    fun updateState() {
        _uiState.value = RegisterUiState(password = EnteredPassword, email = EnteredEmail)
    }

    fun sendRequest(client: OkHttpClient, request: Request, onSuccess: () -> Unit) {
        var temp: Exception? = null
        viewModelScope.launch {
            try {
                val response = makeRequest(client, request)
                println(response)
            } catch (e: IOException) {
                println(e)
                temp = e
            }
        }
        if (temp == null) {
            onSuccess()
        }
    }
}