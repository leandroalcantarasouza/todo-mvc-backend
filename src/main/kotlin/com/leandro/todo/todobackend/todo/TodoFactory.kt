package com.leandro.todo.todobackend.todo

import org.springframework.stereotype.Component

@Component
class TodoFactory {

    fun fromTodoDTO(todoDto: TodoDto) : Todo {
        return Todo(content = todoDto.content.orEmpty(), id = null)
    }

    fun fromTodoDTO(todoDto: TodoDto,todo: Todo) : Todo {
        return Todo(content = todoDto.content.orEmpty(), id = todo.id)
    }
}
