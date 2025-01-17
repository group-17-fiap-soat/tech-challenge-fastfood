package tech.challenge.fastfood.fastfood.application.service.impl

import jakarta.persistence.EntityNotFoundException
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.application.dto.OrderDto
import tech.challenge.fastfood.fastfood.application.dto.OrderItemDto
import tech.challenge.fastfood.fastfood.application.service.CustomerService
import tech.challenge.fastfood.fastfood.application.service.OrderService
import tech.challenge.fastfood.fastfood.application.service.ProductService
import tech.challenge.fastfood.fastfood.domain.port.OrderItemRepositoryPort
import tech.challenge.fastfood.fastfood.domain.port.OrderRepositoryPort
import tech.challenge.fastfood.fastfood.infra.mapper.OrderItemMapper
import tech.challenge.fastfood.fastfood.infra.mapper.OrderMapper
import java.util.*

@Service
class OrderServiceImpl(
    private val orderRepositoryPort: OrderRepositoryPort,
    private val orderItemRepositoryPort: OrderItemRepositoryPort
) : OrderService {

    override fun listOrders(): List<OrderDto>? {
       val orders = orderRepositoryPort.findAll().map(OrderMapper::toDto)
       return  orders.map { order ->
            val orderItems = orderItemRepositoryPort.findAllByOrderId(order.id!!)
            order.copy(orderItems = orderItems.map(OrderItemMapper::toDto))
        }
    }

    override fun getOrderById(id: UUID): OrderDto? {
        return orderRepositoryPort.findById(id)?.let(OrderMapper::toDto)
    }

    override fun createOrder(orderDto: OrderDto): OrderDto {
        validateOrderItems(orderItems = orderDto.orderItems)

        val orderEntity = orderRepositoryPort.save(orderDto)
        val orderItems: List<OrderItemDto> = orderDto.orderItems.map{ it.copy(orderId = orderEntity.id) }
        orderItemRepositoryPort.saveAll(orderItems)
        return OrderMapper.toDto(orderEntity)
    }

    private fun validateOrderItems(orderItems:List<OrderItemDto>) {
        if(orderItems.isEmpty()){
            throw BadRequestException("O pedido deve conter produtos")
        }

        if(orderItems.size != orderItems.distinctBy { it.productId }.size){
            val duplicatedItems = orderItems.groupingBy { it.productId }
                .eachCount()
                .filter { it.value > 1 }
                .keys
                .toList()

            throw BadRequestException("Produto duplicado, considere incrementar a quantidade ao invés de enviá-lo duas vezes - Itens duplicados: ${duplicatedItems}")
        }
    }

}