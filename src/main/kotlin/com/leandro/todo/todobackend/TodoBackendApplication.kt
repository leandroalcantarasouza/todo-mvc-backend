package com.leandro.todo.todobackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class TodoMvcBackendApplication

fun main(args: Array<String>) {
	runApplication<TodoMvcBackendApplication>(*args)
}
