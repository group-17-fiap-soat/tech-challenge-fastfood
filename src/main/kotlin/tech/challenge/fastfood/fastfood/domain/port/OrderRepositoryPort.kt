package tech.challenge.fastfood.fastfood.domain.port

import tech.challenge.fastfood.fastfood.application.dto.OrderDto
import tech.challenge.fastfood.fastfood.domain.model.OrderEntity
import java.util.*

interface OrderRepositoryPort {
    fun findAll(): List<OrderEntity>
    fun findById(id: UUID): OrderEntity?
    fun save(entity: OrderDto): OrderEntity
}