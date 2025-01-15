package tech.challenge.fastfood.fastfood.infra.adapters.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.application.dto.OrderDto
import tech.challenge.fastfood.fastfood.application.service.OrderService
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateOrderRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.CustomerResponseV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.OrderResponseV1
import tech.challenge.fastfood.fastfood.infra.mapper.OrderMapper
import java.util.*

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService
) {

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
    @GetMapping
    fun listOrders(): ResponseEntity<List<OrderDto>> {
        val orders = orderService.listOrders()
        return ResponseEntity.ok(orders)
    }

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
    @GetMapping("/{id}")
    fun getOrderById(
        @PathVariable
        id: UUID
    ): ResponseEntity<OrderResponseV1> {
        val order = orderService.getOrderById(id)
        return if (order != null) {
            val response = OrderMapper.toOrderResponseV1(order)
            ResponseEntity.ok(response)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        }
    }

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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createOrder(
        @RequestBody request: CreateOrderRequestV1
    ): ResponseEntity<OrderResponseV1> {
        val order = OrderMapper.createOrderRequestToDto(request)
        val createdOrder = orderService.createOrder(order)
        val response = OrderMapper.toOrderResponseV1(createdOrder)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }

}