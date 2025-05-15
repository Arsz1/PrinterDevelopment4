package com.example.printerdevelopment4.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.printerdevelopment4.R
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onFileTap: () -> Unit
) {
    val default = displayFontFamily
    Surface (modifier = modifier)
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(240, 240, 240))
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
            ) {
                Column(modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
                ) {
                    Text(text = "Выбранное устройство",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700)
                        )
                    )
                    Text(text = "Таганрог, Некрасовский 42",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(133, 133, 133)
                        )
                    )
                }
                Spacer(modifier = Modifier.width(120.dp))
                Column(modifier = Modifier
                    .padding(start = 20.dp, bottom = 20.dp, top = 20.dp, end = 50.dp)
                ) {
                    Icon(
                        Icons.Filled.Notifications,
                        contentDescription = "Toggle notifications menu",
                        modifier = Modifier
                            .requiredSize(34.dp)
                            .clickable { }
                    )
                }
            }
            Column(modifier = Modifier
                .background(color = Color(240, 240, 240))
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .horizontalScroll(rememberScrollState()),
                ) {
                    InfoBox(imageId = R.drawable.percent, text = "Ваши акции")
                    InfoBox(imageId = R.drawable.wheel, text = "Розыгрыш")
                    InfoBox(imageId = R.drawable.recycle, text = "Помоги планете")
                    InfoBox(imageId = R.drawable.recycle, text = "Помоги планете")
                    InfoBox(imageId = R.drawable.recycle, text = "Помоги планете")
                }
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                    .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp)
                    ),
                ) {
                    Text(
                        modifier = Modifier
                        .padding(15.dp),
                        text = "Баланс баллов",
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(120.dp))
                    Row(modifier = Modifier
                        .padding(top = 15.dp)
                    ) {
                        Text(modifier = Modifier
                            .padding(end = 5.dp),
                            text = "25",
                            color = Color.Black
                        )
                        Image(
                            modifier = Modifier
                                .width(20.dp)
                                .height(20.dp),
                            painter = painterResource(R.drawable.points),
                            contentDescription = null
                        )
                    }
                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp)
                    ),
                ) {
                    StyledButton(text = "Печать", onClick = onFileTap)
                    StyledButton(text = "Ксерокопия", onClick = {})
                    StyledButton(text = "Сканирование", onClick = {})
                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
                    .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp)
                    ),
                ) {
                    Row() {
                        Column() {
                            Text(modifier = Modifier
                                .padding(20.dp)
                                .width(116.dp)
                                .height(54.dp),
                                text = "Скидка 50% печать",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color.Black
                                )
                            )
                            Text(modifier = Modifier
                                .padding(20.dp)
                                .width(116.dp)
                                .height(54.dp),
                                text = "Действует только для новых клиентов",
                                style = TextStyle(
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(99, 124, 247)
                                )
                            )
                        }
                        Image(modifier = Modifier
                            .width(295.dp)
                            .height(180.dp),
                            painter = painterResource(R.drawable.aprinter),
                            contentDescription = null
                        )
                    }
                }

            }
        }
    }

}

@Composable
fun InfoBox(
    imageId: Int,
    text: String
    ) {
    Column(modifier = Modifier
        .padding(start = 15.dp)
        .background(shape = RoundedCornerShape(15.dp), color = Color.White)
        .border(
            width = 2.dp,
            color = Color(99,124, 247),
            shape = RoundedCornerShape(10.dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        Image(modifier = Modifier
            .width(50.dp)
            .height(50.dp),
            painter = painterResource(imageId),
            contentDescription = null
        )
        Text(modifier = Modifier
            .width(80.dp)
            .height(42.dp)
            .padding(bottom = 20.dp, start = 10.dp, end = 10.dp),
            text = text,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight(600),
                color = Color(99, 124, 247)
            )
        )
    }
}

@Composable
fun StyledButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(modifier = modifier
        .fillMaxWidth()
        .padding(15.dp)
        .border(
            width = 2.dp,
            color = Color(red = 99, green = 124, blue = 247),
            shape = RoundedCornerShape(8.dp)
        ),
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            containerColor = Color.White,
            contentColor = Color(red = 99, green = 124, blue = 247),
            disabledContainerColor = Color.DarkGray,
            disabledContentColor = Color(red = 59, green = 104, blue = 227)
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text = text, style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight(600))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    PrinterDevelopment4Theme {
        MainScreen(onFileTap = {})
    }
}