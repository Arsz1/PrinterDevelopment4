package com.example.printerdevelopment4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.printerdevelopment4.ui.AuthorizeScreen
import com.example.printerdevelopment4.ui.BuyScreen
import com.example.printerdevelopment4.ui.EnterScreen
import com.example.printerdevelopment4.ui.FileScreen
import com.example.printerdevelopment4.ui.PrinterScreen
import com.example.printerdevelopment4.ui.RegisterScreen
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme
import com.example.printerdevelopment4.ui.SplashScreen
import com.example.printerdevelopment4.ui.data.OrderItem

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
