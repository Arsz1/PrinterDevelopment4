package com.example.printerdevelopment4.ui.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EnterUiState())
    val uiState: StateFlow<EnterUiState> = _uiState.asStateFlow()

    var EnteredUsername by mutableStateOf("")
    var EnteredPassword by mutableStateOf("")

    fun updateState() {
        _uiState.value = EnterUiState(username = EnteredUsername, password = EnteredPassword)
    }
}