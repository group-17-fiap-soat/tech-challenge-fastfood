package tech.challenge.fastfood.fastfood.infra.exception.handler

import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.ErrorResponseV1
import tech.challenge.fastfood.fastfood.infra.exception.InvalidCustomerDataException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponseV1> {
        return buildError(ex, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(InvalidCustomerDataException::class)
    fun handleInvalidCustomerDataException(ex: InvalidCustomerDataException): ResponseEntity<ErrorResponseV1> {
        return buildError(ex, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponseV1> {
        return buildError(ex, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<ErrorResponseV1> {
        return buildError(ex, HttpStatus.NOT_FOUND)
    }


    private fun buildError(ex: Exception, status: HttpStatus): ResponseEntity<ErrorResponseV1> = ResponseEntity(
        ErrorResponseV1(
            message = ex.message.toString(),
            origin = ex.stackTrace[0].className,
            status = status.toString()
        ),
        status
    )


}
