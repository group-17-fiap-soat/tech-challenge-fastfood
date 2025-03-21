package tech.challenge.fastfood.fastfood.adapters.controllers

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.challenge.fastfood.fastfood.adapters.controllers.operation.OrderOperation
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderMapper
import tech.challenge.fastfood.fastfood.common.dto.request.ChangeOrderStatusRequestV1
import tech.challenge.fastfood.fastfood.common.dto.request.CreateOrderRequestV1
import tech.challenge.fastfood.fastfood.common.dto.response.OrderResponseV1
import tech.challenge.fastfood.fastfood.usecases.order.ChangeOrderStatusUseCase
import tech.challenge.fastfood.fastfood.usecases.order.CreateOrderUseCase
import tech.challenge.fastfood.fastfood.usecases.order.GetOrderByIdUseCase
import tech.challenge.fastfood.fastfood.usecases.order.ListOrderUseCase
import java.util.*

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val listOrderByIdUseCase: ListOrderUseCase,
    private val getOrderByIdUseCase: GetOrderByIdUseCase,
    private val createOrderUseCase: CreateOrderUseCase,
    private val changeOrderStatusUseCase: ChangeOrderStatusUseCase
) : OrderOperation {


    @GetMapping
    override fun listOrders(): ResponseEntity<List<OrderResponseV1>> {
        val orders = listOrderByIdUseCase.execute()
        val response = orders.map(OrderMapper::toOrderResponseV1)
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
        @RequestBody @Valid request: CreateOrderRequestV1
    ): ResponseEntity<OrderResponseV1> {
        val order = OrderMapper.requestToEntity(request)
        val createdOrder = createOrderUseCase.execute(order)
        val response = OrderMapper.toOrderResponseV1(createdOrder)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PutMapping("/{orderId}/status")
    @ResponseStatus(HttpStatus.OK)
    override fun putOrderStatus(
        @PathVariable orderId: UUID,
        @RequestBody request: ChangeOrderStatusRequestV1?
    ): ResponseEntity<OrderResponseV1> {
        val updatedOrder = changeOrderStatusUseCase.execute(orderId, request?.status)
        val response = OrderMapper.toOrderResponseV1(updatedOrder);
        return ResponseEntity.ok().body(response)
    }


}