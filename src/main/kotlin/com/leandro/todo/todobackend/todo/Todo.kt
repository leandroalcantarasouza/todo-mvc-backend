package com.leandro.todo.todobackend.todo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank

@Document("todo")
data class Todo(@Id val id: String? = null,
                val lastUpdateDate: LocalDateTime = LocalDateTime.now(),
                @field:NotBlank val content: String?)

