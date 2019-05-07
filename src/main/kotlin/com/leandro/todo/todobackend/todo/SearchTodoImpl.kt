package com.leandro.todo.todobackend.todo

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.count
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
class SearchTodoImpl(private val mongoTemplate: MongoTemplate): ISearchTodo {

    override fun findAllTodoCountBy(query: Query): Long {
       return mongoTemplate.count(query, Todo::class)
    }

    override fun findAllTodoBy(query: Query): List<Todo> {
        return mongoTemplate.find(query, Todo::class.java)
    }

}
