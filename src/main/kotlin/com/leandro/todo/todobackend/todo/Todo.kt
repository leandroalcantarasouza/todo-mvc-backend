package com.leandro.todo.todobackend.todo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document("todo")
data class Todo(@Id val id: Long,
                val dataUltimaAtualizacao: LocalDateTime = LocalDateTime.now(),
                val conteudo: String)

