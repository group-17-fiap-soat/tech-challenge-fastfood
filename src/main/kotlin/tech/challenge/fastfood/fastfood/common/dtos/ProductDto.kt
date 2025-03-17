package tech.challenge.fastfood.fastfood.common.dtos

import tech.challenge.fastfood.fastfood.common.enums.CategoryEnum
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

data class ProductDto(
    val id: UUID? = null,
    val name: String? = null,
    val description: String? = null,
    val price: BigDecimal? = null,
    val category: CategoryEnum? = null,
    val imageUrl: String? = null,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null
)
