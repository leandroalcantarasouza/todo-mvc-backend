package com.leandro.todo.todobackend.todo

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("todo")
data class Todo(val dataRegistro: LocalDateTime = LocalDateTime.now(),
                val conteudo: String)
