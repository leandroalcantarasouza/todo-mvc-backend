package com.leandro.todo.todobackend.todo.exception

import java.lang.RuntimeException

open class BusinessException(val messageCode: String, val messageArgs: Array<String> = emptyArray()) : RuntimeException()
