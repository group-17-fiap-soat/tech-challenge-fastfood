package tech.challenge.fastfood.fastfood.usecases.order

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderItemGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Order
import java.util.*

@Service
class GetOrderByIdUseCase(
    private val orderGatewayInterface: OrderGatewayInterface,
    private val orderItemGatewayInterface: OrderItemGatewayInterface
) {

    fun execute(id: UUID): Order {
        val order = orderGatewayInterface.findById(id)
            ?: throw EntityNotFoundException("Pedido com o id ${id} não encontrado")

        val orderItems = orderItemGatewayInterface.findAllByOrderId(order.id!!)
        return order.copy(orderItems = orderItems)
    }
}