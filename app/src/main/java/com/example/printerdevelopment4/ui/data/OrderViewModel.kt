package com.example.printerdevelopment4.ui.fileupload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.printerdevelopment4.ui.data.OrderItem
import com.example.printerdevelopment4.ui.data.OrderUiState
import com.example.printerdevelopment4.ui.data.makeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class OrderViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun addOrder(type: String, displayFileName: String, fileName: String, filePath: String, price: Int, format: String, copies: Int, startpage: Int, endpage: Int) {
        _uiState.value.orderItems.add(OrderItem(type = type, displayFileName = displayFileName, fileName = fileName, filePath = filePath, price = price, format = format, copies = copies, startpage = startpage, endpage = endpage))
    }

    fun emptyOrders() {
        _uiState.value.orderItems = mutableListOf()
    }

    fun getOrders(): MutableList<OrderItem> {
        return _uiState.value.orderItems
    }

    fun sendRequest(client: OkHttpClient, request: Request, onSuccess: () -> Unit) {
        var temp: Exception? = null
        viewModelScope.launch {
            try {
                val response = makeRequest(client, request)
                println(response)
                onSuccess()
            } catch (e: IOException) {
                println(e)
                temp = e
            }
        }
    }
}