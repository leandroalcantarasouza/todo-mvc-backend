package com.leandro.todo.todobackend.todo

import com.leandro.todo.todobackend.todo.exception.BusinessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class TodoService(private val todoRepository: ITodoRepository, private val searchTodoImpl: SearchTodoImpl, private val validator: JavaxValidation<Todo>) {

    fun insert(todo: Todo): Todo {
        return todoRepository.save(todo)
    }

    fun getById(id: String): Todo? {
        return todoRepository.findByIdOrNull(id)
    }

    fun deleteById(id: String) {
        todoRepository.deleteById(id)
    }

    fun findByContent(contentFilter: String?, pageable: Pageable): Page<Todo> {
        var query = Query()
        query.with(pageable)
        query.with(Sort.by(Sort.Direction.DESC,"lastUpdateDate"))
        if(!contentFilter.isNullOrEmpty()) {
            query.addCriteria(Criteria.where("content").regex(contentFilter.toString(),"i"))
        }
        var countQueryresult = searchTodoImpl.findAllTodoCountBy(query)
        var returnedElements = searchTodoImpl.findAllTodoBy(query)
        return PageImpl<Todo>(returnedElements, pageable, countQueryresult)
    }

    fun validateTodoInsert(todo: Todo) : Todo {
        if(!todo.id.isNullOrBlank()) {
            throw BusinessException("todo.insert.exist.id.exception")
        }
        validator.validate(todo)
        return todo
    }

    fun validateTodoUpdate(todo: Todo) : Todo {
        if(todo.id.isNullOrBlank()) {
            throw BusinessException("todo.update.without.id.exception")
        }
        validator.validate(todo)
        return todo
    }
}
