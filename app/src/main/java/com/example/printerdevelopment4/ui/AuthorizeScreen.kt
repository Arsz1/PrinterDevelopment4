package com.example.printerdevelopment4.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
fun AuthorizeScreen(
    modifier: Modifier = Modifier,
    onLoginTap: () -> Unit,
    onRegisterTap: () -> Unit
) {
    val default = displayFontFamily
    Surface (modifier = modifier)
    {
        Column(
            modifier = Modifier.fillMaxSize().background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(180.dp))
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
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
                    text = stringResource(R.string.what_we_do),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 15.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 22.sp,
                        color = Color(0, 0, 0)
                    )
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp, end = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(70.dp))
                Button(
                    onClick = onLoginTap,
                    modifier = Modifier
                        .fillMaxWidth(),
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
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = onRegisterTap,
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp, 8.dp, 8.dp, 8.dp),
                    colors = ButtonColors(
                        containerColor = Color(99, 124, 247),
                        contentColor = Color(255, 255, 255),
                        disabledContainerColor = Color(red = 79, green = 104, blue = 227),
                        disabledContentColor = Color(red = 125, green = 125, blue = 125)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.reg_button),
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
fun AuthorizeScreenPreview() {
    PrinterDevelopment4Theme {
        AuthorizeScreen(onLoginTap = {}, onRegisterTap = {})
    }
}