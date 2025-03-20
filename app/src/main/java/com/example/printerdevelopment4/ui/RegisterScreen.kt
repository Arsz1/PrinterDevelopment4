package com.example.printerdevelopment4.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.printerdevelopment4.R
import com.example.printerdevelopment4.ui.data.RegisterViewModel
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = viewModel()
) {
    val enterUiState by registerViewModel.uiState.collectAsState()
    val default = displayFontFamily
    val username = ""
    Surface(modifier = modifier)
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(R.drawable.printerlogo),
                    contentDescription = null,
                    modifier = Modifier
                        .background(
                            color = Color(99, 124, 247),
                            shape = RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp)
                        )
                        .width(100.dp)
                        .height(100.dp)
                )
                Text(
                    text = stringResource(R.string.print_smart),
                    style =
                    TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 70.sp
                    ),
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp, end = 15.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Имя пользователя", textAlign = TextAlign.Left)
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .height(50.dp),
                        value = registerViewModel.EnteredUsername,
                        onValueChange = { registerViewModel.EnteredUsername = it },
                        singleLine = true
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Адрес электронной почты", textAlign = TextAlign.Left)
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp)
                            .height(50.dp),
                        value = registerViewModel.EnteredPassword,
                        onValueChange = { registerViewModel.EnteredPassword = it },
                        singleLine = true
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Пароль", textAlign = TextAlign.Left)
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .height(50.dp),
                        value = registerViewModel.EnteredUsername,
                        onValueChange = { registerViewModel.EnteredUsername = it },
                        singleLine = true
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Повторите пароль", textAlign = TextAlign.Left)
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .height(50.dp),
                        value = registerViewModel.EnteredUsername,
                        onValueChange = { registerViewModel.EnteredUsername = it },
                        singleLine = true
                    )
                }
                Button(
                    onClick = { registerViewModel.updateState() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    shape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp),
                    colors = ButtonColors(
                        containerColor = Color(99, 124, 247),
                        contentColor = Color(255, 255, 255),
                        disabledContainerColor = Color(red = 79, green = 104, blue = 227),
                        disabledContentColor = Color(red = 125, green = 125, blue = 125)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.confirm_button),
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight(400)
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PrinterDevelopment4Theme {
        RegisterScreen()
    }
}