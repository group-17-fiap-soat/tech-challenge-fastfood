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
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.operation.OrderOperation
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.request.CreateOrderRequestV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.CustomerResponseV1
import tech.challenge.fastfood.fastfood.infra.adapters.controllers.response.OrderResponseV1
import tech.challenge.fastfood.fastfood.infra.mapper.OrderMapper
import java.util.*

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val orderService: OrderService
) : OrderOperation {


    @GetMapping
    override fun listOrders(): ResponseEntity<List<OrderDto>> {
        val orders = orderService.listOrders()
        return ResponseEntity.ok(orders)
    }


    @GetMapping("/{id}")
    override fun getOrderById(
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


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createOrder(
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