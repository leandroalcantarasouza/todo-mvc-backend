package com.leandro.todo.todobackend.todo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface ITodoRepository : PagingAndSortingRepository<Todo, String> {

    fun findByContentContainingOrderByLastUpdateDateDesc(content: String, page: Pageable) : Page<Todo>
}


