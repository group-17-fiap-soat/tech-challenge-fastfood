package tech.challenge.fastfood.fastfood.adapters.controllers.operation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import tech.challenge.fastfood.fastfood.common.enums.CategoryEnum
import tech.challenge.fastfood.fastfood.common.dto.request.CreateProductRequestV1
import tech.challenge.fastfood.fastfood.common.dto.request.UpdateProductRequestV1
import tech.challenge.fastfood.fastfood.common.dto.response.CustomerResponseV1
import tech.challenge.fastfood.fastfood.common.dto.response.ErrorResponseV1
import tech.challenge.fastfood.fastfood.common.dto.response.ProductResponseV1

interface ProductOperation {

    @Operation(
        summary = "Cria um novo produto",
        description = "Recebe os dados de um produto no corpo da requisição e cria um novo registro no sistema."
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201", description = "Produto criado com sucesso", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = CustomerResponseV1::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Dados inválidos na requisição",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ErrorResponseV1::class)
            )]
        )]
    )
    fun createProduct(request: CreateProductRequestV1): ResponseEntity<ProductResponseV1>


    @Operation(
        summary = "Atualiza um produto existente",
        description = "Recebe os dados de um produto no corpo da requisição e atualiza um novo registro no sistema."
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200", description = "Produto atualizado com sucesso", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = ProductResponseV1::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Dados inválidos na requisição",
            content = [Content(
                mediaType = "application/json",
                schema = Schema(implementation = ErrorResponseV1::class)
            )]
        )]
    )
    fun putProduct(request: UpdateProductRequestV1): ResponseEntity<ProductResponseV1>

    @Operation(
        summary = "Lista produtos",
        description = "Lista produtos, sendo possível buscar produtos por categoria"
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200", description = "Lista de produtos recuperada com sucesso", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = ProductResponseV1::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Dados inválidos na requisição",
            content = [Content(mediaType = "application/json")]
        )]
    )
    fun findAll(category: CategoryEnum?): ResponseEntity<List<ProductResponseV1>>

    @Operation(
        summary = "Remove um produto pelo id",
        description = "Recebe os dados de um produto no corpo da requisição e cria/atualiza um novo registro no sistema."
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201", description = "Produto criado com sucesso", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = ProductResponseV1::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Dados inválidos na requisição",
            content = [Content(mediaType = "application/json")]
        )]
    )
    fun removeProductById(id: String): ResponseEntity<Void>

}