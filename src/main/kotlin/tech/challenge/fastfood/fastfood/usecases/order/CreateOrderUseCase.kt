package tech.challenge.fastfood.fastfood.usecases.order

import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderItemGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.ProductGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Order
import tech.challenge.fastfood.fastfood.entities.OrderItem

@Service
class CreateOrderUseCase(
    private val orderGatewayInterface: OrderGatewayInterface,
    private val orderItemGatewayInterface: OrderItemGatewayInterface,
    private val productGatewayInterface: ProductGatewayInterface
) {

    @Transactional(rollbackFor = [Exception::class])
    fun execute(order: Order): Order {
        validateOrderItems(orderItems = order.orderItems)

        val orderEntity = orderGatewayInterface.save(order)
        val orderItems: List<OrderItem> = order.orderItems.map { it.copy(orderId = orderEntity.id) }
        val savedOrderItems = orderItemGatewayInterface.saveAll(orderItems)
        val orderItemsWithProductInfo = savedOrderItems.map { orderItem ->
            val productInfo = productGatewayInterface.findByOrderItemId(orderItem.id!!)
            orderItem.copy(product = productInfo)
        }

        return orderEntity.copy(orderItems = orderItemsWithProductInfo)
    }

    private fun validateOrderItems(orderItems: List<OrderItem>) {
        if (orderItems.isEmpty()) {
            throw BadRequestException("O pedido deve conter produtos")
        }

        if (orderItems.size != orderItems.distinctBy { it.product?.id }.size) {
            val duplicatedItems = orderItems.groupingBy { it.product?.id }
                .eachCount()
                .filter { it.value > 1 }
                .keys
                .toList()

            throw BadRequestException("Produto duplicado, considere incrementar a quantidade ao invés de enviá-lo duas vezes - Itens duplicados: ${duplicatedItems}")
        }
    }
}