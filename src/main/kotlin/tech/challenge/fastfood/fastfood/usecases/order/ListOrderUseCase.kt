package tech.challenge.fastfood.fastfood.usecases.order

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderItemGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.PaymentGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Order
import tech.challenge.fastfood.fastfood.entities.PaymentAssociation

@Service
class ListOrderUseCase(
    private val orderGatewayInterface: OrderGatewayInterface,
    private val orderItemGatewayInterface: OrderItemGatewayInterface,
    private val paymentGatewayInterface: PaymentGatewayInterface
) {
    fun execute(): List<Order> {
        val orders = orderGatewayInterface.findAll()
       return orders.map { order ->
            val orderItems = orderItemGatewayInterface.findAllByOrderId(order.id!!)
            val payment = paymentGatewayInterface.findByOrderId(order.id)
            order.copy(orderItems = orderItems, payment = PaymentAssociation(paymentData = payment))
        }
            .filter { it.status != OrderStatusEnum.FINISHED }
            .sortedWith(compareBy<Order> { it.status?.priority }
                .thenBy { it.createdAt })
    }
}
