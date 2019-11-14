package org.chiwooplatform.samples.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.reactive.server.WebTestClient

class SimpleRouteTest {

    lateinit var client: WebTestClient

    @BeforeEach
    fun init() {
        this.client = WebTestClient.bindToRouterFunction(SimpleRoute().route()).build()
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
