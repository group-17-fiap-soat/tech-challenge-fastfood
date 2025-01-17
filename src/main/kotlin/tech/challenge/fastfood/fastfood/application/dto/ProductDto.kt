package tech.challenge.fastfood.fastfood.application.dto

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.UUID

data class ProductDto(
    val id: UUID? = null,
    val name: String? = null ,
    val description: String? = null,
    val price: BigDecimal? = null,
    val category: String? = null,
    val imageUrl: String? = null,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null
)
