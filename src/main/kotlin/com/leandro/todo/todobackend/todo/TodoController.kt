package com.leandro.todo.todobackend.todo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1")
class TodoController(private val todoFacade: TodoFacade) {

    @PostMapping("/todos")
    fun insert(@RequestBody @Valid todo: Todo): ResponseEntity<Todo> {
        val todoInserido = todoFacade.insert(todo)
        return ResponseEntity(todoInserido, HttpStatus.OK)
    }

    @GetMapping("/todos/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Todo> {
        val retorno = todoFacade.getById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(retorno, HttpStatus.OK)
    }

    @PatchMapping("/todos/{id}")
    fun update(@PathVariable id: Long, @RequestBody todo: Todo): ResponseEntity<Todo> {
        val retorno = todoFacade.update(id, todo)
        return ResponseEntity(retorno, HttpStatus.OK)
    }

    @DeleteMapping("/todos/{id}")
    fun deleteById(@PathVariable id: Long) {
        todoFacade.deleteById(id)
    }
}
