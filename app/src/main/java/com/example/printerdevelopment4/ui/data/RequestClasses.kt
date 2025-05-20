package com.example.printerdevelopment4.ui.data

class User(var email: String, var password: String )

class LoginResponse(var message: String, var token: String)

class FileResponse(var message: String, var file: Map<String, Any>)

class FormattedPrint(var token: String, var filePath: String, var options: Map<String, Any>)

class PrintOptions(var format: String, var copies: Int, var startpage: Int, var endpage: Int)

data class OrderItem(
    val type: String,
    val fileName: String,
    val filePath: String,
    val format: String,
    val copies: Int,
    val startpage: Int,
    val endpage: Int,
    val price: Int
)