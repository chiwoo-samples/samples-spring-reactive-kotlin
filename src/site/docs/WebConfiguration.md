
- WebConfiguration.kt 

~~~~
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.config.CorsRegistry

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration {

    @Bean
    fun webMvcConfigure(): WebMvcConfigurer {
        return object : WebMvcConfigurer {


            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://helloworld.org")
                        .allowedMethods("PUT", "DELETE")
                        .allowedHeaders("header1", "header2", "header3")
                        .exposedHeaders("header1", "header2")
                        .allowCredentials(false)
                        .maxAge(3600)
            }

            override fun addViewControllers(registry: ViewControllerRegistry) {
                registry.addViewController("/").setViewName("redirect:/swagger-ui.html")
            }
        }
    }

}
~~~~
