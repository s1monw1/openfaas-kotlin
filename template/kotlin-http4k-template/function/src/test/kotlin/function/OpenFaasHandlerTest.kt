package function

import com.openfaas.function.OpenFaasHandler
import org.assertj.core.api.Assertions.assertThat
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Test


class OpenFaasHandlerTest {

    @Test
    fun `handler returns OK`() {
        val handler = OpenFaasHandler()

        val response = handler(Request(Method.GET, "/"))

        assertThat(response.status).isEqualTo(OK)
        assertThat(response.body.toString()).isEqualTo("Hello OpenFaas World")
    }
}