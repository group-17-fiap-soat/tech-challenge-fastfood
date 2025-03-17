package tech.challenge.fastfood.fastfood.usecases.order

import tech.challenge.fastfood.fastfood.common.dtos.OrderDto
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderItemGatewayInterface
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderItemMapper
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderMapper

class ListOrderByIdUseCase(
    private val orderGatewayInterface: OrderGatewayInterface,
    private val orderItemGatewayInterface: OrderItemGatewayInterface
){

    fun execute(): List<OrderDto>? {
        val orders = orderGatewayInterface.findAll().map(OrderMapper::toDto)
        return orders.map { order ->
            val orderItems = orderItemGatewayInterface.findAllByOrderId(order.id!!)
            order.copy(orderItems = orderItems.map(OrderItemMapper::toDto))
        }
    }
}