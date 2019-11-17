package com.openfaas.entrypoint

import com.openfaas.function.Handler
import com.openfaas.model.IHandler
import com.openfaas.model.Request
import com.sun.net.httpserver.Headers
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import java.nio.charset.Charset

internal const val PORT: Int = 3000

fun main() {
    val server = HttpServer.create(InetSocketAddress(PORT), 0)
    with(server) {
        createContext("/", InvokeHandler(Handler()))
        executor = null // creates a default executor
        start()
    }
}

class InvokeHandler(private val handler: IHandler) : HttpHandler {

    override fun handle(exchange: HttpExchange) {
        val requestBody = when (exchange.requestMethod) {
            "POST" -> exchange.requestBody.bufferedReader().use { it.readText() }
            else -> null
        }
        val headers: Map<String, String> = exchange.requestHeaders.toList().associate {
            it.first to it.second.first()
        }
        val request = Request(requestBody, headers, exchange.requestURI.rawQuery, exchange.requestURI.path)
        val response = handler.handle(request)
        val responseHeaders: Headers = exchange.responseHeaders
        response.contentType?.let {
            if (it.isNotBlank()) responseHeaders.add("Content-Type", it)
        }
        response.headers.forEach(responseHeaders::add)
        val bytesOut = response.body?.toByteArray(Charset.forName("UTF-8"))
        val responseLength = bytesOut?.size?.toLong() ?: 0L
        exchange.sendResponseHeaders(response.statusCode, responseLength)
        bytesOut?.let {
            exchange.responseBody.write(it)
        }
        println("Request / $responseLength bytes written.");
    }

}