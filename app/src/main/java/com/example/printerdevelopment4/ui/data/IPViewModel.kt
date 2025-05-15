package com.example.printerdevelopment4.ui.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class IPViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(IPUiState())
    val uiState: StateFlow<IPUiState> = _uiState.asStateFlow()

    var curIP: String by mutableStateOf("")

    fun updateIP(curIP: String = "") {
        _uiState.value.IP = curIP;
    }
}