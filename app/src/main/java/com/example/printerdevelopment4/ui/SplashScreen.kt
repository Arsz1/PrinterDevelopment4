package com.example.printerdevelopment4.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.printerdevelopment4.R
import com.example.printerdevelopment4.ui.data.IPViewModel
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onConfirmTap: () -> Unit,
    IPViewModel: IPViewModel = viewModel()
    ) {
    val IPUiState by IPViewModel.uiState.collectAsState()
    val default = displayFontFamily
    Box(modifier = Modifier.fillMaxSize()) {
        Surface (
            modifier = modifier
        ) {
            Column(
                modifier = Modifier.fillMaxSize().background(color = Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.printerlogo),
                    contentDescription = null,
                    modifier = Modifier
                        .background(
                            color = Color(99, 124, 247),
                            shape = RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp)
                        )
                        .width(160.dp)
                        .height(160.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                        .border(
                            width = 2.dp,
                            color = Color(red = 99, green = 124, blue = 247),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                        .height(60.dp),
                    value = IPViewModel.curIP,
                    onValueChange = { IPViewModel.curIP = it },
                    singleLine = true,
                    shape = RoundedCornerShape(15.dp),
                    textStyle = TextStyle(color = Color.Black)
                )
                StyledButton(text = "Подтвердить IP", onClick = {
                    onConfirmTap();
                    IPViewModel.updateIP(IPViewModel.curIP);
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    PrinterDevelopment4Theme {
        SplashScreen(onConfirmTap = {})
    }
}