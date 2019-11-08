package com.openfaas.function

import org.http4k.core.HttpHandler
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

class OpenFaasHandler : HttpHandler {
    override fun invoke(request: Request): Response {
        return Response(OK).body("Hello OpenFaas World")
    }
}