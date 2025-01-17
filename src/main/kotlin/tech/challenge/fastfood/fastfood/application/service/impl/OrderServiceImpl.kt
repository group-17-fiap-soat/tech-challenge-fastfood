package tech.challenge.fastfood.fastfood.application.service.impl

import jakarta.persistence.EntityNotFoundException
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tech.challenge.fastfood.fastfood.application.dto.OrderDto
import tech.challenge.fastfood.fastfood.application.dto.OrderItemDto
import tech.challenge.fastfood.fastfood.application.service.CustomerService
import tech.challenge.fastfood.fastfood.application.service.OrderService
import tech.challenge.fastfood.fastfood.application.service.ProductService
import tech.challenge.fastfood.fastfood.domain.port.OrderItemRepositoryPort
import tech.challenge.fastfood.fastfood.domain.port.OrderRepositoryPort
import tech.challenge.fastfood.fastfood.domain.port.ProductRepositoryPort
import tech.challenge.fastfood.fastfood.infra.mapper.OrderItemMapper
import tech.challenge.fastfood.fastfood.infra.mapper.OrderMapper
import java.util.*

@Service
class OrderServiceImpl(
    private val orderRepositoryPort: OrderRepositoryPort,
    private val orderItemRepositoryPort: OrderItemRepositoryPort,
    private val productRepositoryPort: ProductRepositoryPort
) : OrderService {

    override fun listOrders(): List<OrderDto>? {
        val orders = orderRepositoryPort.findAll().map(OrderMapper::toDto)
        return orders.map { order ->
            val orderItems = orderItemRepositoryPort.findAllByOrderId(order.id!!)
            order.copy(orderItems = orderItems.map(OrderItemMapper::toDto))
        }
    }

    override fun getOrderById(id: UUID): OrderDto {
        val order = orderRepositoryPort.findById(id)?.let(OrderMapper::toDto)
            ?: throw EntityNotFoundException("Pedido com o id ${id} não encontrado")

        val orderItems = orderItemRepositoryPort.findAllByOrderId(order.id!!)
        return order.copy(orderItems = orderItems.map(OrderItemMapper::toDto))
    }

    override fun createOrder(orderDto: OrderDto): OrderDto {
        validateOrderItems(orderItems = orderDto.orderItems)

        val orderEntity = orderRepositoryPort.save(orderDto)
        val orderItems: List<OrderItemDto> = orderDto.orderItems.map { it.copy(orderId = orderEntity.id) }
        val savedOrderItems = orderItemRepositoryPort.saveAll(orderItems)
        val orderItemsWithProductInfo = savedOrderItems.map { orderItem ->
            val productInfo = productRepositoryPort.findByOrderItemId(orderItem.id!!)
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