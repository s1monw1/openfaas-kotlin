package com.openfaas.model

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test


class RequestTest {
    @Test
    fun testSingleRequestParameterGetSet() {
        val request = Request(queryRaw = "testParam=testParamValue")
        assertThat(request.query["testParam"]).isEqualTo("testParamValue")
    }

    @Test
    fun testMultipleRequestParametersGetSet() {
        val request = Request(queryRaw = "testParam1=testParamValue1&testParam2=testParamValue2")

        assertThat(request.query["testParam1"]).isEqualTo("testParamValue1")
        assertThat(request.query["testParam2"]).isEqualTo("testParamValue2")
    }

    @Test
    fun testNullRequestParameterGetSet() {
        val request = Request()
        assertThat(request.query["testParam"]).isNull()
    }

    @Test
    fun testEmptyRequestParameterGetSet() {
        val request = Request(queryRaw = "")
        assertThat(request.query["testParam"]).isNull()
    }

    @Test
    fun testRequestRawGetSet() {
        val request = Request(queryRaw = "testRaw=testRawValue")
        assertThat(request.queryRaw).isEqualTo("testRaw=testRawValue")
    }

    @Test
    fun testGetPath() {
        val request = Request(pathRaw = "/test/path")
        assertThat(request.pathRaw).isEqualTo("/test/path")
    }

    @Test
    fun testGetPathWithNullPath() {
        val request = Request()
        assertThat(request.pathRaw).isNull()

    }

    @Test
    fun testParseParametersWithoutAnyParameters() {
        val request = Request()
        assertThat(request.path).hasSize(0)

        val emptyRequest = Request(pathRaw = "")
        assertThat(emptyRequest.path).hasSize(0)

    }

    @Test
    fun testParseParametersWithEvenParameters() {
        val request = Request(pathRaw = "/param1/value1/param2/value2")
        val params = request.path
        assertThat(request.path["param1"]).isEqualTo("value1")
        assertThat(request.path["param2"]).isEqualTo("value2")

    }

    @Test
    fun testParseParametersWithOddParameters() {
        val request = Request(pathRaw = "/param1/value1/param2")
        val params = request.path
        assertThat(request.path["param1"]).isEqualTo("value1")
        assertThat(request.path["param2"]).isEqualTo("")

    }

}