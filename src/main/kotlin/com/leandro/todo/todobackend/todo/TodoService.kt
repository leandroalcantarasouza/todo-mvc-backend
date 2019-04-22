package com.leandro.todo.todobackend.todo

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(private val todoRepository: ITodoRepository) {

    fun insert(todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    fun getById(id: Long): Todo? {
        return todoRepository.findByIdOrNull(id)
    }

    fun deleteById(id: Long) {
        return todoRepository.deleteById(id)
    }

}
