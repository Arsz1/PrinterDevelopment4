package com.example.printerdevelopment4

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.printerdevelopment4.ui.AuthorizeScreen
import com.example.printerdevelopment4.ui.BuyScreen
import com.example.printerdevelopment4.ui.EnterScreen
import com.example.printerdevelopment4.ui.FileScreen
import com.example.printerdevelopment4.ui.MainScreen
import com.example.printerdevelopment4.ui.PrinterScreen
import com.example.printerdevelopment4.ui.ProfileScreen
import com.example.printerdevelopment4.ui.RegisterScreen
import com.example.printerdevelopment4.ui.SplashScreen
import com.example.printerdevelopment4.ui.data.AppViewModel
import com.example.printerdevelopment4.ui.data.EnterViewModel
import com.example.printerdevelopment4.ui.data.IPViewModel
import com.example.printerdevelopment4.ui.fileupload.OrderViewModel

enum class PrinterApp(@StringRes val title: Int, val pageNum: Int) {
    Start(title = R.string.Start, pageNum = 1),
    Authorize(title = R.string.Authorize, pageNum = 2),
    Login(title = R.string.Login, pageNum = 3),
    Register(title = R.string.Register, pageNum = 4),
    Main(title = R.string.Main, pageNum = 5),
    Printer(title = R.string.Printer, pageNum = 6),
    Payment(title = R.string.Payment, pageNum = 7),
    Profile(title = R.string.Profile, pageNum = 8),
    File(title = R.string.file, pageNum = 9)
}

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val navigationItems = listOf(
    BottomNavigationItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = PrinterApp.Main.name
    ),
    BottomNavigationItem(
        title = "Printer",
        icon = Icons.Default.Add,
        route = PrinterApp.Printer.name
    ),
    BottomNavigationItem(
        title = "Cart",
        icon = Icons.Default.ShoppingCart,
        route = PrinterApp.Payment.name
    ),
    BottomNavigationItem(
        title = "Profile",
        icon = Icons.Default.Person,
        route = PrinterApp.Profile.name
    )
)

@Composable
fun BottomNavigationBar(
    isLoggedIn: Boolean,
    navController: NavHostController = rememberNavController()
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    if (isLoggedIn) {
        NavigationBar(
            containerColor = Color.White
        ) {
            navigationItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedNavigationIndex.intValue == index,
                    onClick = {
                        selectedNavigationIndex.intValue = index
                        navController.navigate(item.route)
                    },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = item.title)
                    },
                    label = {
                        Text(
                            item.title,
                            color = if (index == selectedNavigationIndex.intValue)
                                Color.Black
                            else Color.Gray
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.surface,
                        indicatorColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrinterAppBar(
    currentScreen: PrinterApp,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title), color = Color.White) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(99, 124, 247),
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack && currentScreen.pageNum < 5 && currentScreen.pageNum != 1) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun PrinterApp(
    navController: NavHostController = rememberNavController(),
    orderViewModel: OrderViewModel = viewModel(),
    appViewModel: AppViewModel = viewModel(),
    ipViewModel: IPViewModel = viewModel()
) {
    val appViewState = appViewModel.uiState.collectAsState()
    val orderViewState = orderViewModel.uiState.collectAsState()
    val ipViewState = ipViewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = PrinterApp.valueOf(
        backStackEntry?.destination?.route?: PrinterApp.Start.name
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        topBar = {
            PrinterAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                isLoggedIn = appViewState.value.IsLoggedIn,
                navController
            )
        }
    ) { innerPadding ->
        val basic_mod = Modifier
            .fillMaxSize()
            .background(color = Color.White)

        NavHost(
            navController = navController,
            startDestination = PrinterApp.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = PrinterApp.Start.name) {
                SplashScreen(
                    modifier = basic_mod,
                    IPViewModel = ipViewModel,
                    onConfirmTap = {
                        if (ipViewModel.curIP != "") {
                            ipViewModel.updateIP(ipViewModel.curIP)
                            navController.navigate(PrinterApp.Authorize.name)
                        }
                    }
                )
            }
            composable(route = PrinterApp.Authorize.name) {
                AuthorizeScreen(
                    modifier = basic_mod,
                    onLoginTap = { navController.navigate(PrinterApp.Login.name) },
                    onRegisterTap = { navController.navigate(PrinterApp.Register.name) }
                )
                appViewModel.updatePage(currentScreen.pageNum)
            }
            composable(route = PrinterApp.Login.name) {
                EnterScreen(
                    modifier = basic_mod,
                    appViewModel = appViewModel,
                    ipViewState = ipViewState.value,
                    onEnterTap = {
                        navController.navigate(PrinterApp.Main.name)
                    }
                )
                appViewModel.updatePage(currentScreen.pageNum)
            }
            composable(route = PrinterApp.Register.name) {
                RegisterScreen(
                    modifier = basic_mod,
                    ipViewState = ipViewState.value,
                    onRegisterTap = {
                        navController.navigate(PrinterApp.Login.name)
                    }
                )
                appViewModel.updatePage(currentScreen.pageNum)
            }
            composable(route = PrinterApp.Main.name) {
                appViewModel.updateLogIn()
                MainScreen(
                    modifier = basic_mod,
                    onFileTap = {
                        navController.navigate(PrinterApp.File.name)
                    }
                )
                appViewModel.updatePage(currentScreen.pageNum)
            }
            composable(route = PrinterApp.Printer.name) {
                PrinterScreen(modifier = basic_mod)
                appViewModel.updatePage(currentScreen.pageNum)
            }
            composable(route = PrinterApp.Payment.name) {
                BuyScreen(modifier = basic_mod,
                    token = appViewState.value.Token,
                    orders = orderViewState.value.orderItems,
                    ipViewState = ipViewState.value,
                    onConfirmTap = {
                        navController.navigate(PrinterApp.Main.name)
                    }
                )
                appViewModel.updatePage(currentScreen.pageNum)
            }
            composable(route = PrinterApp.Profile.name) {
                ProfileScreen(modifier = basic_mod)
                appViewModel.updatePage(currentScreen.pageNum)
            }
            composable(route = PrinterApp.File.name) {
                FileScreen(
                    modifier = basic_mod,
                    token = appViewState.value.Token,
                    ipViewState = ipViewState.value,
                    orderViewModel = orderViewModel,
                    onConfirmTap = {
                        navController.navigate(PrinterApp.Main.name)
                    }
                )
                appViewModel.updatePage(currentScreen.pageNum)
            }
        }
    }
}
