package org.chiwooplatform.samples.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.FluxExchangeResult
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult
import reactor.test.StepVerifier

@ExtendWith(SpringExtension::class)
@WebFluxTest(controllers = [SimpleController::class])
class SimpleControllerTest {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var client: WebTestClient

    /**
     * @see [SimpleController.getNumbers] getNumbers
     */
    @Test
    fun getNumbers() {
        logger.info("TEST getNumbers")
        client.get()
                .uri("/numbers")
                .exchange()
                .expectStatus().isOk
    }


    private val testClient = WebTestClient.bindToController(SimpleController()).build()

    /**
     * @see [SimpleController.getNumbers] getNumbers
     */
    @Test
    fun getNumbersForFlux() {
        val result: FluxExchangeResult<Int> = testClient.get()
                .uri("/numbers")
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .expectStatus().isOk
                .returnResult()

        StepVerifier.create(result.responseBody)
                .expectSubscription()
                .expectNextCount(97)
                .expectNext(98, 99, 100)
                .expectComplete()
                .verify()

    }

}
