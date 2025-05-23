package tech.challenge.fastfood.fastfood.adapters.controllers.operation

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.common.dto.request.ChangeOrderStatusRequestV1
import tech.challenge.fastfood.fastfood.common.dto.request.CreateOrderRequestV1
import tech.challenge.fastfood.fastfood.common.dto.response.OrderResponseV1
import java.util.*

interface OrderOperation {

    @Operation(
        summary = "Lista os pedidos",
        description = "Recupera os dados de um pedido."
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200", description = "Pedidos encontrados", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = OrderResponseV1::class)
            )]
        ), ApiResponse(
            responseCode = "404",
            description = "Pedidos não encontrados",
            content = [Content(mediaType = "application/json")]
        )]
    )
    fun listOrders(request: HttpServletRequest): ResponseEntity<List<OrderResponseV1>>

    @Operation(
        summary = "Busca um pedido pelo ID",
        description = "Recupera os dados de um pedido utilizando o ID fornecido na URL."
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200", description = "Pedido encontrado", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = OrderResponseV1::class)
            )]
        ), ApiResponse(
            responseCode = "404",
            description = "Pedido não encontrado",
            content = [Content(mediaType = "application/json")]
        )]
    )
    fun getOrderById(
        @PathVariable
        id: UUID
    ): ResponseEntity<OrderResponseV1>


    @Operation(
        summary = "Cria um novo pedido",
        description = "Recebe os dados de um pedido no corpo da requisição e cria um novo registro no sistema."
    )
    @ApiResponses(
        value = [ApiResponse(
            responseCode = "201", description = "Pedido criado com sucesso", content = [Content(
                mediaType = "application/json", schema = Schema(implementation = OrderResponseV1::class)
            )]
        ), ApiResponse(
            responseCode = "400",
            description = "Dados inválidos na requisição",
            content = [Content(mediaType = "application/json")]
        )]
    )
    fun createOrder(
        @RequestBody request: CreateOrderRequestV1
    ): ResponseEntity<OrderResponseV1>


    @Operation(
        summary = "Atualiza o status de um pedido pelo id",
        description = "Caso não receba um status, atualiza para o próximo em ordem, caso contrário, atualiza para o status desejado."
    )
    fun putOrderStatus(
        @PathVariable orderId: UUID,
        @RequestBody request: ChangeOrderStatusRequestV1?
    ): ResponseEntity<OrderResponseV1>
}