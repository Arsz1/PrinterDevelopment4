package com.example.printerdevelopment4.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.printerdevelopment4.R
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onScreenTap: () -> Unit
    ) {
    Box(modifier = Modifier.fillMaxSize()) {
        Surface (
            modifier = modifier
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
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
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clickable
            {
                onScreenTap()
            }
    )
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    PrinterDevelopment4Theme {
        SplashScreen(onScreenTap = {})
    }
}