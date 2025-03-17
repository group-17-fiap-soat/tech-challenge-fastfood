package tech.challenge.fastfood.fastfood.adapters.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.adapters.controllers.operation.OrderOperation
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderMapper
import tech.challenge.fastfood.fastfood.common.dtos.request.CreateOrderRequestV1
import tech.challenge.fastfood.fastfood.common.dtos.response.OrderResponseV1
import tech.challenge.fastfood.fastfood.usecases.order.CreateOrderUseCase
import tech.challenge.fastfood.fastfood.usecases.order.GetOrderByIdUseCase
import tech.challenge.fastfood.fastfood.usecases.order.ListOrderByIdUseCase
import java.util.*

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val listOrderByIdUseCase: ListOrderByIdUseCase,
    private val getOrderByIdUseCase: GetOrderByIdUseCase,
    private val createOrderUseCase: CreateOrderUseCase
) : OrderOperation {


    @GetMapping
    override fun listOrders(): ResponseEntity<List<OrderResponseV1>> {
        val orders = listOrderByIdUseCase.execute()
        val response = orders?.map(OrderMapper::toOrderResponseV1)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }


    @GetMapping("/{id}")
    override fun getOrderById(
        @PathVariable
        id: UUID
    ): ResponseEntity<OrderResponseV1> {
        val order = getOrderByIdUseCase.execute(id)
        val response = OrderMapper.toOrderResponseV1(order)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    override fun createOrder(
        @RequestBody request: CreateOrderRequestV1
    ): ResponseEntity<OrderResponseV1> {
        val order = OrderMapper.createOrderRequestToDto(request)
        val createdOrder = createOrderUseCase.execute(order)
        val response = OrderMapper.toOrderResponseV1(createdOrder)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }


}