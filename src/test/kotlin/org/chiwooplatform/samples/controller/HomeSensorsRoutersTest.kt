package org.chiwooplatform.samples.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@SpringJUnitConfig(value = [HomeSensorsRoutersTest.Config::class])
class HomeSensorsRoutersTest {


    @ComponentScan("org.chiwooplatform.samples.controller")
    @Configuration
    class Config(@Autowired val handler: HomeSensorsHandler) {

        private val logger = LoggerFactory.getLogger(javaClass)

        @Bean
        fun client(): WebTestClient = run {
            logger.info("ClientConfig ------ handler:  {}", handler)
            WebTestClient.bindToRouterFunction(HomeSensorsRouters(handler).deviceRouter()).build()
        }
    }

    @Autowired
    lateinit var client: WebTestClient

    /**
     * @see [HomeSensorsRouters.deviceRouter] deviceRouter
     */
    @Test
    fun testDeviceRouter() {
        client.get()
                .uri("/devices/")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .consumeWith(System.out::println)
    }

}
