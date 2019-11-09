package com.openfaas.function

import com.openfaas.model.IHandler
import com.openfaas.model.IRequest
import com.openfaas.model.IResponse
import com.openfaas.model.Response

class Handler : IHandler {
    override fun handle(request: IRequest): IResponse {
        return Response(body = "Hello OpenFaas World")
    }
}