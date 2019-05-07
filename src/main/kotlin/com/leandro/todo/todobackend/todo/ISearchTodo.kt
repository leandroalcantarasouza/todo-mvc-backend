package com.leandro.todo.todobackend.todo

import org.springframework.data.mongodb.core.query.Query

interface ISearchTodo {

    fun findAllTodoCountBy(query: Query): Long
    fun findAllTodoBy(query: Query): List<Todo>
}
