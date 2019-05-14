package com.leandro.todo.todobackend.todo.exception

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
class RestFieldMessageDTO {

    var field: String? = null

    var message: String? = null

    var rejectedValue: Any? = null

    constructor(message: String) {
        this.message = message
    }

    constructor(field: String, message: String, rejectedValue: Any?) {
        this.field = field
        this.message = message
        this.rejectedValue = rejectedValue
    }
}
