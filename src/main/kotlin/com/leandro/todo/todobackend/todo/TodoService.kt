package com.leandro.todo.todobackend.todo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(private val todoRepository: ITodoRepository) {

    fun insert(todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    fun getById(id: String): Todo? {
        return todoRepository.findByIdOrNull(id)
    }

    fun deleteById(id: String) {
        return todoRepository.deleteById(id)
    }

    fun findByContent(contentFilter: String, pageable: Pageable): Page<Todo> {
        return todoRepository.findByContentContainingOrderByLastUpdateDateDesc(contentFilter, pageable)
    }

}
