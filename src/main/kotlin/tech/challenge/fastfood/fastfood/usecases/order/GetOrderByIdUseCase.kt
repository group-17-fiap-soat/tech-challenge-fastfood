package tech.challenge.fastfood.fastfood.usecases.order

import jakarta.persistence.EntityNotFoundException
import tech.challenge.fastfood.fastfood.common.dtos.OrderDto
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderItemGatewayInterface
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderItemMapper
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderMapper
import java.util.*

class GetOrderByIdUseCase(
    private val orderGatewayInterface: OrderGatewayInterface,
    private val orderItemGatewayInterface: OrderItemGatewayInterface
) {

    fun execute(id: UUID): OrderDto {
        val order = orderGatewayInterface.findById(id)?.let(OrderMapper::toDto)
            ?: throw EntityNotFoundException("Pedido com o id ${id} não encontrado")

        val orderItems = orderItemGatewayInterface.findAllByOrderId(order.id!!)
        return order.copy(orderItems = orderItems.map(OrderItemMapper::toDto))
    }
}