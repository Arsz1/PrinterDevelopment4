package com.example.printerdevelopment4.ui.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun updatePage(pageNum: Int = 0) {
        _uiState.value.CurrentPageNum = pageNum;
    }

    fun updateLogIn() {
        _uiState.value.IsLoggedIn = true;
    }

    fun updateLogOut() {
        _uiState.value.IsLoggedIn = false;
    }

    fun updateToken(token: String) {
        _uiState.value.Token = token
    }
}