package tech.challenge.fastfood.fastfood.common.dtos

import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum
import java.time.OffsetDateTime
import java.util.*

data class OrderDto(
    val id: UUID? = null,
    val orderNumber: Long? = null,
    val orderItems: List<tech.challenge.fastfood.fastfood.common.dtos.OrderItemDto> = listOf(),
    val customerId: UUID? = null,
    val status: tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum? = null,
    val orderDate: OffsetDateTime? = null,
    val finishedAt: OffsetDateTime? = null,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null
)
