package tech.challenge.fastfood.fastfood.infra.adapters.controllers.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import tech.challenge.fastfood.fastfood.infra.exception.InvalidCustomerDataException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCustomerDataException::class)
    fun handleInvalidCustomerDataException(ex: InvalidCustomerDataException): ResponseEntity<Map<String, String>> {
        val error = mapOf("error" to ex.message.orEmpty())
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

}
