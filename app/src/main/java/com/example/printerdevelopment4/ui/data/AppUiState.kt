package com.example.printerdevelopment4.ui.data

data class AppUiState(
    var IsLoggedIn: Boolean = false,
    var CurrentPageNum: Int = 1,
    var Token: String = ""
)