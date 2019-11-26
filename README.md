# OpenFaaS Kotlin templates

This repository contains two Kotlin templates for OpenFaaS.
```
$ faas-cli template pull https://github.com/s1monw1/openfaas-kotlin
$ faas new --list

Languages available as templates:
- kotlin
- kotlin-http4k
```


## kotlin template 

This template runs without any dependency and bootstraps its own ``com.sun.net.httpserver.HttpServer``.

### Trying the template

```
$ faas-cli template pull https://github.com/s1monw1/openfaas-kotlin
$ faas new --lang kotlin <fn-name>
```

### Example usage

Example writing a successful message:

```kotlin
package com.openfaas.function

import com.openfaas.model.*

class Handler : IHandler {
    override fun handle(request: IRequest): IResponse {
        return Response(body = "Hello OpenFaas World")
    }
}
```

Example writing a custom status code

```kotlin
package com.openfaas.function

import com.openfaas.model.*

class Handler : IHandler {
    override fun handle(request: IRequest): IResponse {
        return Response(body = "Hello OpenFaas World", statusCode = 202)
    }
}
```

Example writing an error / failure.

```kotlin
package com.openfaas.function

import com.openfaas.model.*

class Handler : IHandler {
    override fun handle(request: IRequest): IResponse {
        return Response(body = "Error saying hello", statusCode = 500)
    }
}
```

The `body` will be written to the client along with a HTTP 500 status code.


## kotlin-http4k template

This template is backed by [http4k](https://www.http4k.org/), a lightweight function http toolkit for Kotlin.


### Trying the template

```
$ faas-cli template pull https://github.com/s1monw1/openfaas-kotlin
$ faas new --lang kotlin-http4k <fn-name>
```

### Example usage

Example writing a successful message:

```kotlin
package com.openfaas.function

import org.http4k.core.*
import org.http4k.core.Status.Companion.OK

class OpenFaasHandler : HttpHandler {
    override fun invoke(request: Request): Response {
        return Response(OK).body("Hello OpenFaas World")
    }
}
```
Example writing a custom status code

```kotlin
import org.http4k.core.*
import org.http4k.core.Status.Companion.ACCEPTED

class OpenFaasHandler : HttpHandler {
    override fun invoke(request: Request): Response {
        return Response(ACCEPTED).body("Hello OpenFaas World")
    }
}
```

Example writing an error / failure.

```kotlin
import org.http4k.core.*
import org.http4k.core.Status.Companion.INTERNAL_SERVER_ERROR

class OpenFaasHandler : HttpHandler {
    override fun invoke(request: Request): Response {
        return Response(INTERNAL_SERVER_ERROR).body("Error saying hello")
    }
}
```

The `body` will be written to the client along with a HTTP 500 status code.
