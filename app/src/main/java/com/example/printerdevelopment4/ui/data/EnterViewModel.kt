package com.example.printerdevelopment4.ui.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.Moshi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class EnterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EnterUiState())
    val uiState: StateFlow<EnterUiState> = _uiState.asStateFlow()

    var EnteredEmail by mutableStateOf("")
    var EnteredPassword by mutableStateOf("")

    fun updateState() {
        _uiState.value = EnterUiState(email = EnteredEmail, password = EnteredPassword)
    }

    fun sendRequest(moshi: Moshi, client: OkHttpClient, request: Request, appViewModel: AppViewModel, onSuccess: () -> Unit) {
        var temp: Exception? = null
        viewModelScope.launch {
            try {
                val response = makeRequest(client, request)
                val jsonAdapterResponse = moshi.adapter(LoginResponse::class.java)
                val jsonResponse = jsonAdapterResponse.fromJson(response)
                if (jsonResponse != null) {
                    appViewModel.updateToken(jsonResponse.token)
                }
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