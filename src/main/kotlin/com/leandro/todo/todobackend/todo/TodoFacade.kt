package com.leandro.todo.todobackend.todo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class TodoFacade (private val todoService: TodoService) {

    fun insert(todo: Todo): Todo {
        return todoService.insert(todo)
    }

    fun getById(id: Long): Todo? {
        return todoService.getById(id)
    }

    fun deleteById(id: Long) {
        return todoService.deleteById(id)
    }

    fun update(id: Long, todo: Todo): Todo {
        val todoOriginal = this.getById(id) ?: throw IllegalArgumentException("Element not found")
        val todoModificado = Todo(id= todoOriginal.id, conteudo = todo.conteudo)
        return todoService.insert(todoModificado)
    }

    fun findByConteudo(conteudoFilter: String, pageable: Pageable): Page<Todo> {
        return todoService.findByConteudo(conteudoFilter, pageable)
    }
}
