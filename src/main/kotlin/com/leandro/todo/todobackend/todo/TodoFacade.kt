package com.leandro.todo.todobackend.todo

import com.leandro.todo.todobackend.todo.exception.TodoNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class TodoFacade (private val todoService: TodoService,
                  private val todoFactory: TodoFactory) {

    fun insert(todo: TodoDto): Todo {
        val todoToBeInserted = todoFactory.fromTodoDTO(todo)
        return todoService.insert(todoService.validateTodoInsert(todoToBeInserted))
    }

    fun getById(id: String): Todo? {
        return todoService.getById(id) ?: throw TodoNotFoundException()
    }

    fun deleteById(id: String) {
        todoService.deleteById(id)
    }

    fun update(id: String, todo: TodoDto): Todo {
        val originalTodo = this.getById(id) ?: throw TodoNotFoundException()

        val modifiedTodo = todoService.validateTodoUpdate(todoFactory.fromTodoDTO(todo, originalTodo))
        return todoService.insert(modifiedTodo)
    }

    fun findByContent(contentFilter: String?, pageSize: Int): Page<Todo> {
        if(pageSize < 0) {
            throw IllegalArgumentException("Page size must not be below zero")
        }
        val pageable = PageRequest.of(pageSize, 10)
        return todoService.findByContent(contentFilter, pageable)
    }
}
