package function

import com.openfaas.function.Handler
import com.openfaas.model.Request
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class HandlerTest {

    @Test
    fun `handler returns OK`() {
        val handler = Handler()

        val response = handler.handle(Request(null))

        assertThat(response.statusCode).isEqualTo(200)
        assertThat(response.body.toString()).isEqualTo("Hello OpenFaas World")
    }
}