package com.leandro.todo.todobackend.todo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class TodoFacade (private val todoService: TodoService,
                  private val todoFactory: TodoFactory) {

    fun insert(todo: TodoDto): Todo {
        val todoToBeInserted = todoFactory.fromTodoDTO(todo)
        return todoService.insert(todoToBeInserted)
    }

    fun getById(id: String): Todo? {
        return todoService.getById(id)
    }

    fun deleteById(id: String) {
        return todoService.deleteById(id)
    }

    fun update(id: String, todo: Todo): Todo {
        val originalTodo = this.getById(id) ?: throw IllegalArgumentException("Element not found")
        val modifiedTodo = Todo(id= originalTodo.id, content = todo.content)
        return todoService.insert(modifiedTodo)
    }

    fun findByContent(contentFilter: String, pageable: Pageable): Page<Todo> {
        return todoService.findByContent(contentFilter, pageable)
    }
}
