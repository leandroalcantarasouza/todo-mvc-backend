package com.leandro.todo.todobackend.todo.exception

import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.validation.Errors
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.Instant
import java.util.*
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

@ControllerAdvice
class DefaultExceptionHandler(val messageSource: MessageSource) {

    val ERROR = "error"
    val VIOLATIONS = "violations"
    val TICKET = "ticket"
    val VALIDATION_FAILURE = "ValidationFailure"
    val UNKNOWN_ERROR = "UnknownError"


    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ResponseBody
    fun handleBusinessException(ex: BusinessException): Map<String, Any> {
        val map = HashMap<String, Any>()

        map[ERROR] = resolveMessage(VALIDATION_FAILURE, ex.messageArgs)

        val violations = ArrayList<Any>()
        violations.add(RestFieldMessageDTO(resolveMessage(ex.messageCode, ex.messageArgs)))
        map[VIOLATIONS] = violations

        return map
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    fun handleUncaughtException(ex: Exception): Map<String, Any> {
        val map = HashMap<String, Any>()
        map[ERROR] = resolveMessage(UNKNOWN_ERROR)
        val timeInMillis = Instant.now().toEpochMilli()
        map[TICKET] = timeInMillis
        return map
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleConstraintViolationException(ex: ConstraintViolationException): Map<String, Any> {
        val map = HashMap<String, Any>()
        map[ERROR] = resolveMessage(VALIDATION_FAILURE)
        map[VIOLATIONS] = convertConstraintViolationsInRestFieldMessage(ex.constraintViolations)

        return map
    }

    private fun resolveMessage(code: String, vararg args: Any): String {
        return messageSource.getMessage(code, args, Locale.ENGLISH)
    }

    private fun resolveMessage(fieldError: FieldError): String {
        return messageSource.getMessage(fieldError, Locale.ENGLISH)
    }

    private fun convertError(errors: Errors): List<RestFieldMessageDTO> {

        val result = ArrayList<RestFieldMessageDTO>()

        for (fieldError in errors.fieldErrors) {
            val dto = RestFieldMessageDTO(fieldError.field, resolveMessage(fieldError), fieldError.rejectedValue)
            result.add(dto)
        }
        return result
    }

    private fun convertConstraintViolationsInRestFieldMessage(constraintViolations: Set<ConstraintViolation<*>>): List<RestFieldMessageDTO> {
        val result = ArrayList<RestFieldMessageDTO>()
        for (constraintViolation in constraintViolations) {
            result.add(RestFieldMessageDTO(constraintViolation.propertyPath.toString(),
                constraintViolation.message,
                constraintViolation.invalidValue))
        }
        return result
    }
}

