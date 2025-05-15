package com.example.printerdevelopment4.ui

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.printerdevelopment4.ui.data.IPUiState
import com.example.printerdevelopment4.ui.fileupload.DropDownItem
import com.example.printerdevelopment4.ui.fileupload.FileFormat
import com.example.printerdevelopment4.ui.fileupload.FileViewModel
import com.example.printerdevelopment4.ui.fileupload.OrderViewModel
import com.example.printerdevelopment4.ui.theme.PrinterDevelopment4Theme
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.IOException


@Composable
fun FileScreen(
    modifier: Modifier = Modifier,
    fileViewModel: FileViewModel = viewModel(),
    orderViewModel: OrderViewModel = viewModel(),
    ipViewState: IPUiState = IPUiState(),
    onConfirmTap: () -> Unit,
    token: String
) {
    val context = LocalContext.current
    var tempFile by rememberSaveable { mutableStateOf<String?>(null) }
    var fileName by rememberSaveable { mutableStateOf<String?>(null) }
    var fileType by rememberSaveable { mutableStateOf<String?>(null) }
    var selectedFormat by rememberSaveable { mutableStateOf("Формат")}
    var chosenFile by rememberSaveable { mutableStateOf<String>("Выбор файла")}
    val density = LocalDensity.current

    // Create a launcher for the file picker
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri!!.getScheme().equals("content")) {
                val cursor = context.contentResolver.query(uri, null, null, null, null)
                try {
                    if (cursor != null) {
                        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        if (cursor.moveToFirst()) {
                            fileName = cursor.getString(nameIndex)
                            val cutt = fileName?.lastIndexOf('.')
                            if ((cutt != -1) && (cutt != null)) {
                                fileType = fileName?.substring(cutt+1)
                            }
                        }
                    }
                } finally {
                    cursor?.close()
                }
                if (fileName == null) {
                    fileName = uri.path
                    val cutt = fileName?.lastIndexOf('/')
                    if ((cutt != -1) && (cutt != null)) {
                        fileName = fileName?.substring(cutt+1)
                    }
                }
            }
            val file = uri?.let { uriToFile(context = context, uri = it, fileName = fileName) }
            tempFile = file.toString()
            chosenFile = fileName.toString()
        }
    )

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
            Column(modifier = Modifier
                .background(color = Color(240, 240, 240))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                        .border(
                            width = 2.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(15.dp)
                        ),
                ) {
                    Row() {
                        Column() {
                            Text(text = "Возможные форматы файла",
                                modifier = Modifier
                                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(133, 133, 133)
                                )
                            )
                            Text(text = "PDF  WORD EXCEL TXT",
                                modifier = Modifier
                                    .padding(start = 20.dp, end = 20.dp),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(10, 8, 8)
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Выберите файл для печати",
                                modifier = Modifier
                                    .padding(20.dp),
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(133, 133, 133)
                                )
                            )
                        }
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(text = "5₽/страница",
                                modifier = Modifier
                                    .padding(20.dp),
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(10, 8, 8)
                                )
                            )
                        }
                    }
                    StyledButton(text = chosenFile,
                        onClick =
                        {
                            filePickerLauncher.launch("*/*")
                        }
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Формат печати",
                            modifier = Modifier
                                .padding(10.dp),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                color = Color.Black
                            )
                        )
                        FileFormat(
                            buttonText = selectedFormat,
                            dropDownItems = listOf(
                                DropDownItem("A4"),
                                DropDownItem("A5")
                            ),
                            onItemClick = {
                                selectedFormat = it.text
                            }
                        )
                    }
                    Spacer(Modifier.weight(0.05f))
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Начальная страница",
                            modifier = Modifier
                                .padding(10.dp),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                color = Color.Black
                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused && fileViewModel.enteredStartPage == "№ стр") {
                                        fileViewModel.enteredStartPage = ""
                                    }
                                }
                                .border(
                                    width = 2.dp,
                                    color = Color(red = 99, green = 124, blue = 247),
                                    shape = RoundedCornerShape(15.dp)
                                ),
                            shape = RoundedCornerShape(15.dp),
                            value = fileViewModel.enteredStartPage,
                            onValueChange = { fileViewModel.enteredStartPage = it },
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            singleLine = true
                        )
                    }
                    Spacer(Modifier.weight(0.05f))
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Конечная страница",
                            modifier = Modifier
                                .padding(10.dp),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                color = Color.Black
                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused && fileViewModel.enteredEndPage == "№ стр") {
                                        fileViewModel.enteredEndPage = ""
                                    }
                                }
                                .border(
                                    width = 2.dp,
                                    color = Color(red = 99, green = 124, blue = 247),
                                    shape = RoundedCornerShape(15.dp)
                                ),
                            shape = RoundedCornerShape(15.dp),
                            value = fileViewModel.enteredEndPage,
                            onValueChange = { fileViewModel.enteredEndPage = it },
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            singleLine = true
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(Modifier.weight(1f))
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                            .border(
                                width = 2.dp,
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .padding(start = 5.dp, end = 5.dp, bottom = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Кол-во копий",
                            modifier = Modifier
                                .padding(10.dp),
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                color = Color.Black
                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(shape = RoundedCornerShape(15.dp), color = Color.White)
                                .onFocusChanged { focusState ->
                                    if (focusState.isFocused && fileViewModel.enteredCopies == "Кол-во") {
                                        fileViewModel.enteredCopies = ""
                                    }
                                }
                                .border(
                                    width = 2.dp,
                                    color = Color(red = 99, green = 124, blue = 247),
                                    shape = RoundedCornerShape(15.dp)
                                ),
                            shape = RoundedCornerShape(15.dp),
                            value = fileViewModel.enteredCopies,
                            onValueChange = { fileViewModel.enteredCopies = it },
                            textStyle = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(600),
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            singleLine = true
                        )
                    }
                    Spacer(Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.weight(0.25f))
            StyledButton(
                text = "Подтвердить",
                onClick =
                {
                    val client = OkHttpClient()
                    val body = MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("token", token)
                        .addFormDataPart("file", tempFile, File(tempFile.toString()).asRequestBody())
                        .build()
                    val request = Request.Builder()
                        .url("http://" + ipViewState.IP + ":3000/upload")
                        .post(body)
                        .build()
                    try {
                        fileViewModel.sendRequest(client, request, orderViewModel
                        ) { internalFileName: String, internalFilePath: String, price: Int, orderViewModel2: OrderViewModel ->
                            orderViewModel2.addOrder(
                                "Печать" + fileType.toString().uppercase() + "файла",
                                internalFileName, internalFilePath, price, selectedFormat, fileViewModel.enteredCopies.toInt(), fileViewModel.enteredStartPage.toInt(), fileViewModel.enteredEndPage.toInt()
                            )
                            onConfirmTap()
                        }
                        onConfirmTap()
                    }
                    catch (e: IOException){
                        val error = e
                    }
                    catch (e: Exception) {
                        val error = e
                    }
                },
                enabled = !(((fileViewModel.enteredCopies == "Кол-во") or (fileViewModel.enteredStartPage == "№ стр") or (fileViewModel.enteredStartPage == "№ стр") or (chosenFile == "Выбор файла"))
                        or ((fileViewModel.enteredCopies == "") or (fileViewModel.enteredStartPage == "") or (fileViewModel.enteredStartPage == "") or (selectedFormat == "Формат")))
            )
            Spacer(modifier = Modifier.weight(0.25f))
        }
    }

}

private fun uriToFile(context: Context, fileName: String?, uri: Uri): File? {
    val inputStream = context.contentResolver.openInputStream(uri)!!
    try {
        val externalDir = context.getExternalFilesDir(null)
        val dir = File(externalDir.toString() + File.separator + "OnePrintUploads")
        if (!dir.exists() or !dir.isDirectory()) {
            val created = dir.mkdirs()
            if (!created) {
                throw IOException("Error creating directory!")
            }
        }
        val file = File(externalDir.toString() + File.separator + "OnePrintUploads" + File.separator + fileName);
        file.createNewFile()
        file.outputStream().use { output ->
            inputStream.copyTo(output)
        }
        return file
    }
    catch (ex: Exception) {
        println(ex)
        return null
    }
}

@Preview(showBackground = true)
@Composable
fun FileScreenPreview() {
    PrinterDevelopment4Theme {
        FileScreen(token = "", onConfirmTap = {})
    }
}