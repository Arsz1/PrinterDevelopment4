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
import com.example.printerdevelopment4.ui.data.IPUiState
import com.example.printerdevelopment4.ui.data.IPViewModel
import com.example.printerdevelopment4.ui.data.RegisterViewModel
import com.example.printerdevelopment4.ui.data.User
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.okhttp.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = viewModel(),
    ipViewState: IPUiState = IPUiState(),
    onRegisterTap: () -> Unit
) {
    val registerUiState by registerViewModel.uiState.collectAsState()
    var passVis by remember { mutableStateOf(false) }
    var passVis2 by remember { mutableStateOf(false) }
    val default = displayFontFamily
    val JSON = "application/json; charset=utf-8".toMediaType()
    var error = ""
    Surface(modifier = modifier)
    {
        Column(
            modifier = Modifier.fillMaxSize().background(color = Color.White),
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
                        fontFamily = FontFamily.SansSerif,
                        color = Color.Black,
                        fontSize = 70.sp
                    )
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
                    Text(text = "Адрес электронной почты", textAlign = TextAlign.Left, color = Color.Black)
                    OutlinedTextField(
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
                        shape = RoundedCornerShape(15.dp),
                        textStyle = TextStyle(color = Color.Black),
                        value = registerViewModel.EnteredEmail,
                        onValueChange = { registerViewModel.EnteredEmail = it },
                        singleLine = true
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Пароль", textAlign = TextAlign.Left, color = Color.Black)
                    OutlinedTextField(
                        value = registerViewModel.EnteredPassword,
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
                        onValueChange = { registerViewModel.EnteredPassword = it},
                        shape = RoundedCornerShape(15.dp),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true
                    )
                }
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "Повторите пароль", textAlign = TextAlign.Left, color = Color.Black)
                    OutlinedTextField(
                        value = registerViewModel.EnteredSecPass,
                        visualTransformation = if (!passVis2) {
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
                                if (!passVis2) {
                                    Icons.Filled.Visibility
                                } else {
                                    Icons.Filled.VisibilityOff
                                },
                                contentDescription = "Toggle password visibility",
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .requiredSize(28.dp)
                                    .clickable { passVis2 = !passVis2 }
                            )
                        },
                        onValueChange = { registerViewModel.EnteredSecPass = it},
                        shape = RoundedCornerShape(15.dp),
                        textStyle = TextStyle(color = Color.Black),
                        singleLine = true
                    )
                }
                Button(
                    onClick = {
                        val client = OkHttpClient()
                        val user = User(registerViewModel.EnteredEmail, registerViewModel.EnteredPassword)
                        val moshi = Moshi.Builder()
                            .add(KotlinJsonAdapterFactory()).build()
                        val jsonAdapterRequest = moshi.adapter(User::class.java)
                        val jsonRequest = jsonAdapterRequest.toJson(user)
                        val body: RequestBody = jsonRequest.toRequestBody(JSON)
                        val request = Request.Builder().url("http://" + ipViewState.IP + ":3000/register").post(body).build()
                        try {
                            registerViewModel.sendRequest(client, request, onRegisterTap)
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
                        text = stringResource(R.string.confirm_button),
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PrinterDevelopment4Theme {
        RegisterScreen(onRegisterTap = {})
    }
}