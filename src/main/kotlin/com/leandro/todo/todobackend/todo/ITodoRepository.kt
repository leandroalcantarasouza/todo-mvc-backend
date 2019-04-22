package com.leandro.todo.todobackend.todo

import org.springframework.data.repository.CrudRepository

interface ITodoRepository : CrudRepository<Todo, Long>
