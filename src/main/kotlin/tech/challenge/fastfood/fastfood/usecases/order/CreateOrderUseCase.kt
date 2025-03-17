package tech.challenge.fastfood.fastfood.usecases.order

import org.apache.coyote.BadRequestException
import org.springframework.transaction.annotation.Transactional
import tech.challenge.fastfood.fastfood.common.dtos.OrderDto
import tech.challenge.fastfood.fastfood.common.dtos.OrderItemDto
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderItemGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.ProductGatewayInterface
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderItemMapper
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderMapper

class CreateOrderUseCase(
    private val orderGatewayInterface: OrderGatewayInterface,
    private val orderItemGatewayInterface: OrderItemGatewayInterface,
    private val productGatewayInterface: ProductGatewayInterface
) {

    @Transactional(rollbackFor = [Exception::class])
    fun execute(orderDto: OrderDto): OrderDto {
        validateOrderItems(orderItems = orderDto.orderItems)

        val orderEntity = orderGatewayInterface.save(orderDto)
        val orderItems: List<OrderItemDto> = orderDto.orderItems.map { it.copy(orderId = orderEntity.id) }
        val savedOrderItems = orderItemGatewayInterface.saveAll(orderItems)
        val orderItemsWithProductInfo = savedOrderItems.map { orderItem ->
            val productInfo = productGatewayInterface.findByOrderItemId(orderItem.id!!)
            orderItem.copy(product = productInfo)
        }

        return OrderMapper.toDto(orderEntity).copy(orderItems = orderItemsWithProductInfo.map(OrderItemMapper::toDto))
    }

    private fun validateOrderItems(orderItems: List<OrderItemDto>) {
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