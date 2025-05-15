package com.example.printerdevelopment4.ui

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
import com.example.printerdevelopment4.ui.data.OrderItem
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme

@Composable
fun PrinterScreen(
    modifier: Modifier = Modifier
) {
    val default = displayFontFamily
    Surface (modifier = modifier)
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(240, 240, 240)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(modifier = Modifier
                .width(150.dp)
                .height(80.dp)
                .padding(20.dp),
                text = "Принтеры",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    color = Color.Black
                )
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PrinterScreenPreview() {
    PrinterDevelopment4Theme {
        PrinterScreen()
    }
}