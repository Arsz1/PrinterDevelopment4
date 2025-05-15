package com.example.printerdevelopment4.ui.data

import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun makeRequest(client: OkHttpClient, request: Request): String = suspendCoroutine { continuation ->
    client.newCall(request).enqueue(object: Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            continuation.resumeWithException(e)
            e.printStackTrace()
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            response.use {
                //onSuccess()
                continuation.resume(response.body!!.string())
            }
        }
    })
}
