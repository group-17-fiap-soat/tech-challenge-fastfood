package tech.challenge.fastfood.fastfood.entities

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

data class Product(
    var id: UUID? = null,
    var name: String? = null,
    var description: String? = null,
    var price: BigDecimal? = null,
    var category: String? = null,
    var imageUrl: String? = null,
    var createdAt: OffsetDateTime? = OffsetDateTime.now(),
    var updatedAt: OffsetDateTime? = OffsetDateTime.now()
)
