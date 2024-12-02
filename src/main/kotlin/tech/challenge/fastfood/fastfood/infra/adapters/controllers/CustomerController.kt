package tech.challenge.fastfood.fastfood.infra.adapters.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.application.service.CustomerService
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.AuthCustomerRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateCustomerRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.CustomerResponseV1
import tech.challenge.fastfood.fastfood.infra.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.infra.mapper.CustomerMapper
import java.util.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    @Operation(
        summary = "Cria um novo cliente",
        description = "Recebe os dados de um cliente no corpo da requisição e cria um novo registro no sistema."
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201", description = "Cliente criado com sucesso", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = CustomerResponseV1::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Dados inválidos na requisição",
            content = [Content(mediaType = "application/json")]
        )]
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(
        @RequestBody request: CreateCustomerRequestV1
    ): ResponseEntity<CustomerResponseV1> {
        val customer = CustomerMapper.createCustomerRequestToDto(request)
        val createdCustomer = customerService.createCustomer(customer)
        val response = CustomerMapper.toCustomerResponseV1(createdCustomer)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @Operation(
        summary = "Autentica um cliente pelo CPF",
        description = "Autentica um cliente no sistema utilizando o CPF enviado no corpo da requisição."
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200", description = "Autenticação bem-sucedida", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = CustomerResponseV1::class)
            )]
        ), ApiResponse(
            responseCode = "401",
            description = "Autenticação falhou: CPF não encontrado",
            content = [Content(mediaType = "application/json")]
        )]
    )
    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    fun authenticate(
        @RequestBody
        authRequest: AuthCustomerRequestV1
    ): ResponseEntity<CustomerResponseV1> {
        val customer = customerService.getCustomerByCpf(
            checkNotNull(authRequest.cpf) {
                throw InvalidCustomerDataException("CPF tem que ser preenchido.")
            }
        )
        return if (customer != null) {
            val response = CustomerMapper.toCustomerResponseV1(customer)
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }
    }

    @Operation(
        summary = "Busca um cliente pelo ID",
        description = "Recupera os dados de um cliente utilizando o ID fornecido na URL."
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200", description = "Cliente encontrado", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = CustomerResponseV1::class)
            )]
        ), ApiResponse(
            responseCode = "404",
            description = "Cliente não encontrado",
            content = [Content(mediaType = "application/json")]
        )]
    )
    @GetMapping("/{id}")
    fun getCustomerById(
        @PathVariable
        id: UUID
    ): ResponseEntity<CustomerResponseV1> {
        val customer = customerService.getCustomerById(id)
        return if (customer != null) {
            val response = CustomerMapper.toCustomerResponseV1(customer)
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }
}
