package com.openfaas.model

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test


class ResponseTest {

    @Test
    fun testResponseHeaderSetGetValue() {
        val response = Response(headers = mapOf("X-Key" to "value"))
        assertThat(response.headers["X-Key"]).isEqualTo("value")
    }

    @Test
    fun testResponseHeaderGetValueKeyMissIsNull() {
        val response = Response()
        assertThat(response.headers["No-Such-Key"]).isNull()

    }

    @Test
    fun testResponseGetSetContentType() {
        val response = Response(contentType = "application/json")
        assertThat(response.contentType).isEqualTo("application/json")
    }

    @Test
    fun testResponseStatusCodeDefaultValue() {
        val response = Response(statusCode = 200)
        assertThat(response.statusCode).isEqualTo(200)
    }
}