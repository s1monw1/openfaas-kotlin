package com.openfaas.model

import java.net.URLDecoder

interface IHandler {
    fun handle(request: IRequest): IResponse
}

interface IRequest {
    val body: String?
    val headers: Map<String, String>
    val queryRaw: String?
    val query: Map<String, String>
    val pathRaw: String?
    fun getHeader(key: String): String?
}

interface IResponse {
    val body: String?
    val headers: Map<String, String>
    val contentType: String?
    val statusCode: Int
}

class Request(
    override val body: String?,
    override val headers: Map<String, String> = mapOf(),
    override val queryRaw: String? = null,
    override val pathRaw: String? = null
) : IRequest {

    override val query: Map<String, String> = parseQueryParameters()

    override fun getHeader(key: String): String? {
        return headers[key]
    }

    private fun toQueryPair(pair: String): Pair<String, String>? {
        val param = pair.split("[=]".toRegex())
        val encoding: String = System.getProperty("file.encoding")
        return if (param.isNotEmpty()) {
            val key: String = URLDecoder.decode(param[0], encoding)
            if (param.size > 1) {
                val value: String = URLDecoder.decode(param[1], encoding)
                key to value
            } else null
        } else null
    }

    private fun parseQueryParameters(): Map<String, String> {
        return queryRaw?.split("[&]".toRegex())
            ?.mapNotNull(::toQueryPair)
            ?.toMap().orEmpty()
    }
}

class Response(
    override val statusCode: Int = 200,
    override val body: String? = null,
    override val headers: Map<String, String> = mapOf(),
    override val contentType: String? = null
) : IResponse