package tech.challenge.fastfood.fastfood.adapters.controllers.operation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import tech.challenge.fastfood.fastfood.common.dto.request.AuthCustomerRequestV1
import tech.challenge.fastfood.fastfood.common.dto.request.CreateCustomerRequestV1
import tech.challenge.fastfood.fastfood.common.dto.response.CustomerResponseV1
import java.util.*

interface CustomerOperation {

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
    fun createCustomer(
        @RequestBody request: CreateCustomerRequestV1
    ): ResponseEntity<CustomerResponseV1>


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
    fun authenticate(
        @RequestBody
        authRequest: AuthCustomerRequestV1
    ): ResponseEntity<CustomerResponseV1>


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
    ): ResponseEntity<CustomerResponseV1>



}