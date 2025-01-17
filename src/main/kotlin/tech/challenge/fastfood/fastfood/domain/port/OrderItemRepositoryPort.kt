package tech.challenge.fastfood.fastfood.domain.port

import tech.challenge.fastfood.fastfood.application.dto.OrderItemDto
import tech.challenge.fastfood.fastfood.domain.model.OrderItemEntity
import java.util.UUID

interface OrderItemRepositoryPort {
    fun save(entity: OrderItemDto): OrderItemEntity
    fun saveAll(entityList: List<OrderItemDto>): List<OrderItemEntity>
    fun findAllByOrderId(id: UUID): List<OrderItemEntity>
}