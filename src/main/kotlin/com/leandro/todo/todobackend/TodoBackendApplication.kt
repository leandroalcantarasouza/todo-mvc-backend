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

@SpringBootApplication
@EnableMongoRepositories
class TodoMvcBackendApplication {


    @Bean
    fun corsFilter(): FilterRegistrationBean<CorsFilter> {
        val source = UrlBasedCorsConfigurationSource()
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedOrigins = listOf("*")
        corsConfiguration.allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
        source.registerCorsConfiguration("/**", corsConfiguration)
        val filterRegistrationBean = FilterRegistrationBean(CorsFilter(source))
        filterRegistrationBean.order = OrderedFilter.REQUEST_WRAPPER_FILTER_MAX_ORDER
        return filterRegistrationBean
    }

}

fun main(args: Array<String>) {
    runApplication<TodoMvcBackendApplication>(*args)
}
