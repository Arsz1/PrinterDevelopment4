package com.example.printerdevelopment4.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    val default = displayFontFamily
    Surface (modifier = modifier.verticalScroll(rememberScrollState()))
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(240, 240, 240)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .background(shape = RoundedCornerShape(15.dp), color = Color(99, 124, 247))
                .border(
                    width = 2.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                ),
            ) {
                Row() {
                    Column() {
                        Text(modifier = Modifier
                            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                            .height(66.dp),
                            text = "Персональная скидка" + "\n" + "15%",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(600),
                                color = Color.White
                            )
                        )
                        Text(modifier = Modifier
                            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                            .height(78.dp),
                            text = "Баланс бонусов" + "\n" + "25",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight(600),
                                color = Color.White
                            )
                        )
                    }
                }
            }
            StyledButton(text = "Сохранённые документы", onClick = {})
            StyledButton(text = "Сохранённые карты", onClick = {})
            StyledButton(text = "Акции", onClick = {})
            StyledButton(text = "Настройки уведомлений", onClick = {})
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(240, 240, 240)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    text = "Служба поддержки 24/7",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        color = Color(133, 133, 133)
                    )
                )
                Text(modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                    text = "+7 952 812 00 61",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600),
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                )
                StyledButton(text = "Выйти из профиля", onClick = {})
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    PrinterDevelopment4Theme {
        ProfileScreen()
    }
}