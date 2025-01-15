package tech.challenge.fastfood.fastfood.application.service.impl

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.application.dto.OrderDto
import tech.challenge.fastfood.fastfood.application.service.OrderService
import tech.challenge.fastfood.fastfood.domain.port.OrderRepositoryPort
import tech.challenge.fastfood.fastfood.infra.mapper.OrderMapper
import java.util.*

@Service
class OrderServiceImpl(
    private val orderRepositoryPort: OrderRepositoryPort
): OrderService {

    override fun listOrders(): List<OrderDto>? {
        return orderRepositoryPort.findAll().map(OrderMapper::toDto)
    }

    override fun getOrderById(id: UUID): OrderDto? {
        return orderRepositoryPort.findById(id)?.let(OrderMapper::toDto)
    }

    override fun createOrder(orderDto: OrderDto): OrderDto {
        return OrderMapper.toDto(orderRepositoryPort.save(orderDto))
    }

}