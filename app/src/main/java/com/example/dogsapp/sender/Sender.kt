package com.example.dogsapp.sender

import android.text.Editable
import com.squareup.okhttp.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.*

class Sender {
    companion object {
        private const val base: String = "https://guidesplatform-production.up.railway.app"

        fun getUser(): String {
            return URL("$base/user").readText()
        }

        fun log(login: Editable, password: Editable): Boolean {
            val client = OkHttpClient()
            client.protocols = listOf(Protocol.HTTP_1_1);

            val data = MediaType.parse("application/json; charset=utf-8")
            val body = RequestBody.create(data, "{\"email\":\"$login\", \"password\":\"$password\"}")
            val request = Request.Builder()
                .url("$base/log")
                .post(body)
                .build()

            val response = client.newCall(request).execute()

            return response.isSuccessful
        }

        fun reg(login: Editable, password: Editable, email: Editable): Boolean {
            val client = OkHttpClient()
            client.protocols = listOf(Protocol.HTTP_1_1);

            val data = MediaType.parse("application/json; charset=utf-8")
            val body = RequestBody.create(data, "{\"email\":\"$email\", \"password\":\"$password\", \"username\":\"$login\"}")
            val request = Request.Builder()
                .url("$base/reg")
                .post(body)
                .build()

            val response = client.newCall(request).execute()

            return response.isSuccessful
        }

        fun getAll(): Response {
            val client = OkHttpClient()
            client.protocols = listOf(Protocol.HTTP_1_1);

            val request = Request.Builder()
                .url("$base/Guides")
                .get()
                .build()

            return client.newCall(request).execute()
        }
    }
}