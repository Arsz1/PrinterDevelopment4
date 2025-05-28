package com.example.printerdevelopment4.ui.fileupload

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.printerdevelopment4.ui.data.FileResponse
import com.example.printerdevelopment4.ui.data.makeRequest
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class FileViewModel(): ViewModel() {
    var enteredStartPage by mutableStateOf("№ стр")
    var enteredEndPage by mutableStateOf("№ стр")
    var enteredCopies by mutableStateOf("Кол-во")

    fun sendRequest(client: OkHttpClient, request: Request, orderViewModel: OrderViewModel, onSuccess: (String, String, Int, OrderViewModel) -> Unit) {
        var temp: Exception? = null
        var jsonMap: Map<String, Any>? = null
        viewModelScope.launch {
            try {
                val response = makeRequest(client, request)
                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory()).build()
                val jsonAdapterResponse = moshi.adapter(FileResponse::class.java)
                val jsonResponse = jsonAdapterResponse.fromJson(response)
                jsonMap = jsonResponse?.file!!
                jsonMap?.forEach { item ->
                    println(item)
                }
                println(response)
            } catch (e: Exception) {
                println(e)
                temp = e
            }
            if (temp == null) {
                val filename = jsonMap!!.getOrElse("filename") {}.toString()
                val path = jsonMap!!.getOrElse("path") {}.toString()
                val price = if ((enteredStartPage == "0") and (enteredEndPage == "0")){
                    15 /* TODO */
                } else {
                    (enteredEndPage.toInt() - enteredStartPage.toInt() + 1) * 5
                }
                onSuccess(filename, path, price, orderViewModel)
            }
        }
    }
}