package com.leandro.todo.todobackend.todo

import org.springframework.stereotype.Component
import javax.validation.ConstraintViolationException
import javax.validation.Validator

@Component
class JavaxValidation<T>(val validator: Validator?) {

    fun validate(entidade: T) {
        val constraintViolations = validator?.validate(entidade)
        if (constraintViolations != null && constraintViolations.isNotEmpty()!!) {
            throw ConstraintViolationException(constraintViolations)
        }
    }

    fun validate(colecao: Collection<T>) {
        for (entidade in colecao) {
            this.validate(entidade)
        }
    }
}

