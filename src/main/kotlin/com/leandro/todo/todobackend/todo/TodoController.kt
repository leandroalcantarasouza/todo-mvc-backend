package com.leandro.todo.todobackend.todo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1")
class TodoController(private val todoFacade: TodoFacade) {

    @PostMapping("/todo")
    fun insert(@RequestBody @Valid todo: Todo): ResponseEntity<Todo> {
        val todoInserido = todoFacade.insert(todo)
        return ResponseEntity(todoInserido, HttpStatus.OK)
    }

    @GetMapping("/todos/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<Todo> {
        val retorno = todoFacade.getById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(retorno, HttpStatus.OK)
    }
}
