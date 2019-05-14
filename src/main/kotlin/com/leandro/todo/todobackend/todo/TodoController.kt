package com.leandro.todo.todobackend.todo

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1")
class TodoController(private val todoFacade: TodoFacade) {

    @PostMapping("/todos")
    fun insert(@RequestBody todo: TodoDto): ResponseEntity<Todo> {
        val insertedTodo = todoFacade.insert(todo)
        return ResponseEntity(insertedTodo, OK)
    }

    @GetMapping("/todos/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Todo> {
        val returnedResults = todoFacade.getById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(returnedResults, OK)
    }

    @PatchMapping("/todos/{id}")
    fun update(@PathVariable id: String, @RequestBody todo: TodoDto): ResponseEntity<Todo> {
        val returnedResults = todoFacade.update(id, todo)
        return ResponseEntity(returnedResults, OK)
    }

    @DeleteMapping("/todos/{id}")
    fun deleteById(@PathVariable id: String) {
        todoFacade.deleteById(id)
    }

    @GetMapping("/todos")
    fun findByContent(contentFilter: String?, pageSize: Int): ResponseEntity<Page<Todo>> {
        val retorno = todoFacade.findByContent(contentFilter, pageSize)
        return ResponseEntity(retorno, OK)
    }
}
