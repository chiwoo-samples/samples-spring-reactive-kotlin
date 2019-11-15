package org.chiwooplatform.samples.controller

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient

class SimpleRouteTest {

    companion object {

        lateinit var client: WebTestClient

        @BeforeAll
        @JvmStatic
        internal fun beforeAll() {
            client = WebTestClient.bindToRouterFunction(SimpleRoute().route()).build()
        }
    }

    /**
     * @see [SimpleRoute.route] route
     */
    @Test
    fun whenRequestToRouteThenStatusShouldBeOk() {
        client.get()
                .uri("/route")
                .exchange()
                .expectStatus().isOk
    }

    /**
     * @see [SimpleRoute.route] route
     */
    @Test
    fun whenRequestToRouteThenAssertionShouldBeOK() {
        client.get()
                .uri("/route")
                .exchange()
                .expectBody()
                .json("[1, 2, 3]")
    }

}
