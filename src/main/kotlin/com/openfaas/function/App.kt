package com.openfaas.function

import org.http4k.core.Method
import org.http4k.core.Method.GET
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer


fun main() {
    val handler = OpenFaasHandler()
    routes(
        "/" bind GET to handler
    ).asServer(SunHttp(3000)).start()
}