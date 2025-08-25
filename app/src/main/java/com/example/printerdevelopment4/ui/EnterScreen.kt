package com.example.printerdevelopment4.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.printerdevelopment4.R
import com.example.printerdevelopment4.ui.data.AppViewModel
import com.example.printerdevelopment4.ui.data.EnterViewModel
import com.example.printerdevelopment4.ui.data.IPUiState
import com.example.printerdevelopment4.ui.data.User
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

@Composable
fun EnterScreen(
    modifier: Modifier = Modifier,
    enterViewModel: EnterViewModel = viewModel(),
    appViewModel: AppViewModel,
    ipViewState: IPUiState = IPUiState(),
    onEnterTap: () -> Unit
) {
    val enterUiState by enterViewModel.uiState.collectAsState()
    val state = remember { TextFieldState() }
    val default = displayFontFamily
    val JSON = "application/json; charset=utf-8".toMediaType()
    var error by remember { mutableStateOf("")}
    var passVis by remember { mutableStateOf(false) }
    val onFail: (Exception) -> Unit = { e ->
        error = e.toString()
    }
    Surface(modifier = modifier)
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .weight(0.5f),
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
                    text = stringResource(R.string.oneprint),
                    style =
                    TextStyle(
                        fontFamily = default,
                        fontSize = 70.sp,
                        color = Color.Black
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
                    Text(modifier = Modifier.padding(start = 10.dp), text = "Адрес электронной почты", textAlign = TextAlign.Left, color = Color.Black)
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .border(
                                width = 2.dp,
                                color = Color(red = 99, green = 124, blue = 247),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                            .height(60.dp),
                        value = enterViewModel.EnteredEmail,
                        onValueChange = { enterViewModel.EnteredEmail = it },
                        textStyle = TextStyle(color = Color.Black),
                        shape = RoundedCornerShape(15.dp),
                        singleLine = true
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(modifier = Modifier.padding(start = 10.dp), text = "Пароль", textAlign = TextAlign.Left, color = Color.Black)
                    OutlinedTextField(
                        value = enterViewModel.EnteredPassword,
                        visualTransformation = if (!passVis) {
                            PasswordVisualTransformation()
                        } else {
                            VisualTransformation.None
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp)
                            .border(
                                width = 2.dp,
                                color = Color(red = 99, green = 124, blue = 247),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                            .height(60.dp),
                        trailingIcon = {
                            Icon(
                                if (!passVis) {
                                    Icons.Filled.Visibility
                                } else {
                                    Icons.Filled.VisibilityOff
                                },
                                contentDescription = "Toggle password visibility",
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .requiredSize(28.dp)
                                    .clickable { passVis = !passVis }
                            )
                        },
                        onValueChange = { enterViewModel.EnteredPassword = it},
                        shape = RoundedCornerShape(15.dp),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true
                    )
                }
                Button(
                    onClick = {
                        val client = OkHttpClient()
                        val user = User(enterViewModel.EnteredEmail, enterViewModel.EnteredPassword)
                        val moshi = Moshi.Builder()
                            .add(KotlinJsonAdapterFactory()).build()
                        val jsonAdapterRequest = moshi.adapter(User::class.java)
                        val jsonRequest = jsonAdapterRequest.toJson(user)
                        val body: RequestBody = jsonRequest.toRequestBody(JSON)
                        val request = Request.Builder().url("http://" + ipViewState.IP + ":3000/login").post(body).build()
                        try {
                            enterViewModel.sendRequest(moshi, client, request, appViewModel, onEnterTap, onFail)
                        }
                        catch (e: IOException){
                            error = "Ошибка регистрации"
                        }
                        catch (e: Exception) {
                            val error = e
                        }
                    },
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
                        text = stringResource(R.string.enter_button),
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight(400)
                        )
                    )
                }
                if (error != "") {
                    Row(
                        modifier = Modifier
                            .padding(10.dp),
                        verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = error,
                            style = TextStyle(
                                fontSize = 14.sp,
                                color = Color(200, 0, 0)
                            )
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp),
                    verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "Забыли пароль?",
                        style = TextStyle(
                            fontSize = 14.sp
                        )
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        text = "Восстановить",
                        style = TextStyle(
                            color = Color(99, 124, 247),
                            fontSize = 14.sp
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EnterScreenPreview() {
    PrinterDevelopment4Theme {
        EnterScreen(onEnterTap = {}, appViewModel = viewModel())
    }
}