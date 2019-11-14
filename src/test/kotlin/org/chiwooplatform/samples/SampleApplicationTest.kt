package org.chiwooplatform.samples

import org.chiwooplatform.samples.controller.SimpleRoute
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
class SampleApplicationTest {

    val logger: Logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var simpleRoute: SimpleRoute

    @Test
    internal fun testInstances() {
        assertNotNull(simpleRoute)
        logger.info("simpleRoute: {}", simpleRoute)
    }
}
