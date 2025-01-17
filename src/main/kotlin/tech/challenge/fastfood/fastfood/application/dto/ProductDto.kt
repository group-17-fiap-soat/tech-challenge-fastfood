package tech.challenge.fastfood.fastfood.application.dto

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class ProductDto(
    val id: UUID? = null,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val category: String,
    val imageUrl: String?,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null
)
