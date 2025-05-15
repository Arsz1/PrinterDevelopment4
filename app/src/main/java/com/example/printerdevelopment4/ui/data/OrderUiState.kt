package com.example.printerdevelopment4.ui.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.printerdevelopment4.PrinterApp

data class OrderUiState(
    var orderItems: MutableList<OrderItem> = mutableListOf()
)