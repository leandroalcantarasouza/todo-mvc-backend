package com.leandro.todo.todobackend.todo

import org.springframework.stereotype.Service

@Service
class TodoFacade (private val todoService: TodoService) {

    fun insert(todo: Todo): Todo {
        return todoService.insert(todo)
    }

    fun getById(id: String): Todo? {
        return todoService.getById(id)
    }
}
