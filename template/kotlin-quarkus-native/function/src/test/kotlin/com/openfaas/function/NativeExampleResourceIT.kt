package com.openfaas.function

import io.quarkus.test.junit.NativeImageTest
import org.junit.jupiter.api.Disabled

@Disabled
@NativeImageTest
class NativeExampleResourceIT : HandlerTest()