package com.leandro.todo.todobackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.filter.OrderedFilter
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.time.LocalDate
import javax.validation.Validation
import javax.validation.Validator

@SpringBootApplication
@EnableMongoRepositories
@EnableSwagger2
class TodoMvcBackendApplication {

    @Bean
    fun corsFilter(): FilterRegistrationBean<CorsFilter> {
        val source = UrlBasedCorsConfigurationSource()
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedOrigins = listOf("*")
        corsConfiguration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS", "HEAD", "TRACE", "CONNECT")
        source.registerCorsConfiguration("/**", corsConfiguration)
        val filterRegistrationBean = FilterRegistrationBean(CorsFilter(source))
        filterRegistrationBean.order = OrderedFilter.REQUEST_WRAPPER_FILTER_MAX_ORDER
        return filterRegistrationBean
    }

    @Bean
    fun validator() : Validator? {
       val factory = Validation.buildDefaultValidatorFactory()
       return factory.validator
    }

    @Bean
    fun petApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .host("localhost:8080")
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.leandro.todo.todobackend"))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false)
    }

    private fun apiInfo(): ApiInfo {

        return ApiInfoBuilder()
            .title("Documentacao da api de caso do sgo v1")
            .version("0.0.1-SNAPSHOT")
            .description("Gerado em " + LocalDate.now())
            .build()
    }

}

fun main(args: Array<String>) {
    runApplication<TodoMvcBackendApplication>(*args)
}
