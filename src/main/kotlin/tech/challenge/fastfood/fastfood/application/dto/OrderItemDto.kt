package tech.challenge.fastfood.fastfood.application.dto

import java.time.OffsetDateTime
import java.util.*

data class OrderItemDto(
    var id: UUID? = null,
    var product: ProductDto? = null,
    var orderId: UUID? = null,
    var quantity: Int? = null,
    var createdAt: OffsetDateTime? = null,
    var updatedAt: OffsetDateTime? = null
)
