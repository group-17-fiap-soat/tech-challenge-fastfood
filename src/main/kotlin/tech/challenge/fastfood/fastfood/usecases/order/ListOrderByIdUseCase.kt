package tech.challenge.fastfood.fastfood.usecases.order

import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderItemGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Order

class ListOrderByIdUseCase(
    private val orderGatewayInterface: OrderGatewayInterface,
    private val orderItemGatewayInterface: OrderItemGatewayInterface
){

    fun execute(): List<Order>? {
        val orders = orderGatewayInterface.findAll()
        return orders.map { order ->
            val orderItems = orderItemGatewayInterface.findAllByOrderId(order.id!!)
            order.copy(orderItems = orderItems)
        }
    }
}