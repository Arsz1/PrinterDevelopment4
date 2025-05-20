package com.example.printerdevelopment4.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.printerdevelopment4.ui.data.FormattedPrint
import com.example.printerdevelopment4.ui.data.IPUiState
import com.example.printerdevelopment4.ui.data.OrderItem
import com.example.printerdevelopment4.ui.fileupload.OrderViewModel
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

val orderItems = listOf(
    OrderItem(
        type = "Печать JPG файла",
        fileName = "bomberman-white.jpg",
        filePath = "/uploads/file-1745490603395-990607806.jpg",
        format = "A5",
        copies = 1,
        startpage = 0,
        endpage = 0,
        price = 10
    )
)

@Composable
fun ListOfOrders(
    modifier: Modifier = Modifier,
    orders: List<OrderItem>
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        orders.forEach { orderItem ->
            Row(modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp)
            ) {
                Column() {
                    Text(modifier = Modifier
                        .width(305.dp)
                        .height(22.dp)
                        .padding(start = 20.dp, end = 20.dp),
                        text = orderItem.type,
                        textAlign = TextAlign.Left,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color.Black
                        )
                    )
                    Text(modifier = Modifier
                        .width(305.dp)
                        .height(22.dp)
                        .padding(start = 20.dp, end = 20.dp, top = 5.dp),
                        text = orderItem.fileName,
                        textAlign = TextAlign.Left,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(133,133,133)
                        )
                    )
                }
                Column(modifier.padding(end = 20.dp)) {
                    Text(
                        modifier = Modifier.width(41.dp),
                        text = orderItem.price.toString() + " ₽",
                        textAlign = TextAlign.Right,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight(600),
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}

fun ConfirmOrders(
    orderViewModel: OrderViewModel,
    onConfirmTap: () -> Unit,
    token: String,
    ipViewState: IPUiState = IPUiState(),
    orders: List<OrderItem>
) {
    orders.forEach { orderItem ->
        val JSON = "application/json; charset=utf-8".toMediaType()
        val client = OkHttpClient()
        val optionsJSON = mapOf("format" to orderItem.format, "copies" to orderItem.copies, "startpage" to orderItem.startpage, "endpage" to orderItem.endpage)
        val format = FormattedPrint(token = token, filePath = orderItem.filePath, options = optionsJSON)
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()
        val jsonAdapterRequest2 = moshi.adapter(FormattedPrint::class.java)
        val jsonRequest = jsonAdapterRequest2.toJson(format)
        val body: RequestBody = jsonRequest.toRequestBody(JSON)
        val request = Request.Builder()
            .url("http://" + ipViewState.IP + ":3000/print")
            .post(body)
            .build()
        try {
            orderViewModel.sendRequest(client, request, onConfirmTap)
        }
        catch (e: IOException){
            val error = e
        }
        catch (e: Exception) {
            val error = e
        }
    }
}

@Composable
fun BuyScreen(
    modifier: Modifier = Modifier,
    orderViewModel: OrderViewModel = viewModel(),
    ipViewState: IPUiState = IPUiState(),
    onConfirmTap: () -> Unit,
    token: String,
    orders: List<OrderItem>
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
                text = "Корзина",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    color = Color.Black
                )
            )
            ListOfOrders(
                modifier = Modifier
                    .fillMaxWidth(),
                //.verticalScroll(rememberScrollState()),
                orders = orders
            )
            Spacer(modifier.weight(1f))
            StyledButton(text = "Подтвердить оплату",
                onClick =
                {
                    ConfirmOrders(
                        orderViewModel = orderViewModel,
                        ipViewState = ipViewState,
                        onConfirmTap = onConfirmTap,
                        token = token,
                        orders = orders
                    )
                }
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BuyScreenPreview() {
    PrinterDevelopment4Theme {
        BuyScreen(orders = mutableListOf(
            OrderItem(
                type = "Печать JPG файла",
                fileName = "bomberman-white.jpg",
                filePath = "/uploads/file-1745490603395-990607806.jpg",
                format = "A5",
                copies = 1,
                startpage = 0,
                endpage = 0,
                price = 10
            ),
            OrderItem(type = "Печать JPG файла",
                fileName = "bomberman-white.jpg",
                filePath = "/uploads/file-1745490603395-990607806.jpg",
                format = "A5",
                copies = 1,
                startpage = 0,
                endpage = 0,
                price = 10
            )
        ),
            token = "",
            onConfirmTap = {}
        )
    }
}