package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.net.HttpURLConnection
import java.net.URL

// 两个测试地址
private const val HTTPS_URL = "https://httpbin.org/get?room=A203"
private const val HTTP_URL = "http://httpbin.org/get?room=A203"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var result by remember { mutableStateOf("等待请求") }
                var busy by remember { mutableStateOf(false) }
                val scrollState = remember { ScrollState(0) }

                fun request(address: String) {
                    busy = true
                    result = "请求中：$address"
                    Thread {
                        val response = fetch(address)
                        runOnUiThread {
                            result = response
                            busy = false
                        }
                    }.start()
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(scrollState)
                ) {
                    Text("姓名：陈帆")
                    Text("实验二 网络访问")

                    Button(
                        onClick = { request(HTTPS_URL) },
                        enabled = !busy
                    ) {
                        Text("发送HTTPS请求")
                    }

                    Button(
                        onClick = { request(HTTP_URL) },
                        enabled = !busy
                    ) {
                        Text("发送HTTP请求")
                    }

                    Text(text = result)
                }
            }
        }
    }
}

// 网络请求函数，定义在类外部，全局可调用
private fun fetch(address: String): String {
    return try {
        val url = URL(address)
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.connectTimeout = 5000
        conn.readTimeout = 5000

        val responseCode = conn.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            val response = conn.inputStream.bufferedReader().use { it.readText() }
            "响应码：$responseCode\n\n$response"
        } else {
            "请求失败，响应码：$responseCode"
        }
    } catch (e: Exception) {
        "请求异常：${e.message}"
    }
}
