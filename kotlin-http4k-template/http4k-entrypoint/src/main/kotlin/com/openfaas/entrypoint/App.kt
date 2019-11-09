package com.openfaas.entrypoint

import com.openfaas.function.OpenFaasHandler
import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.PATCH
import org.http4k.core.Method.POST
import org.http4k.core.Method.PUT
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun main() {
    val handler = OpenFaasHandler()
    routes(
        "/" bind routes(
            GET to handler,
            POST to handler,
            DELETE to handler,
            PUT to handler,
            PATCH to handler
        )
    ).asServer(SunHttp(3000)).start()
}