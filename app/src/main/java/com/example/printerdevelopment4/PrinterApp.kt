package com.example.printerdevelopment4

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.printerdevelopment4.ui.AuthorizeScreen
import com.example.printerdevelopment4.ui.EnterScreen
import com.example.printerdevelopment4.ui.RegisterScreen
import com.example.printerdevelopment4.ui.SplashScreen

enum class PrinterApp() {
    Start,
    Authorize,
    Login,
    Register
}

@Composable
fun PrinterApp(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(

    ) { innerPadding ->
        val basic_mod = Modifier.fillMaxSize().padding(15.dp)

        NavHost(
            navController = navController,
            startDestination = PrinterApp.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PrinterApp.Start.name) {
                SplashScreen(
                    modifier = basic_mod,
                    onScreenTap = { navController.navigate(PrinterApp.Authorize.name) }
                )
            }
            composable(route = PrinterApp.Authorize.name) {
                AuthorizeScreen(
                    modifier = basic_mod,
                    onLoginTap = { navController.navigate(PrinterApp.Login.name) },
                    onRegisterTap = { navController.navigate(PrinterApp.Register.name) }
                )
            }
            composable(route = PrinterApp.Login.name) {
                EnterScreen(modifier = basic_mod)
            }
            composable(route = PrinterApp.Register.name) {
                RegisterScreen(modifier = basic_mod)
            }
        }
    }
}
