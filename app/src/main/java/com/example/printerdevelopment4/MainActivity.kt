package com.example.printerdevelopment4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            PrinterDevelopment4Theme {
               PrinterApp()
            }
        }
    }
}
