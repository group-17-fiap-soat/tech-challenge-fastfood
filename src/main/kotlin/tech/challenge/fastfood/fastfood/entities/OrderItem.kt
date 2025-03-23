package tech.challenge.fastfood.fastfood.entities

import java.time.OffsetDateTime
import java.util.*

data class OrderItem (
    var id: UUID? = null,
    val product: Product? = null,
    var quantity: Int? = null,
    var orderId: UUID? = null,
    var createdAt: OffsetDateTime? = OffsetDateTime.now(),
    var updatedAt: OffsetDateTime? = OffsetDateTime.now()
)