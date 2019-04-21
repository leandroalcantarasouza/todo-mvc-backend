package com.leandro.todo.todomvcbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoMvcBackendApplication

fun main(args: Array<String>) {
	runApplication<TodoMvcBackendApplication>(*args)
}
