package tech.challenge.fastfood.fastfood.common.dtos.request

import tech.challenge.fastfood.fastfood.common.enums.CategoryEnum
import java.math.BigDecimal
import java.util.*

data class UpdateProductRequestV1 (
    val id: UUID? = null,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val category: CategoryEnum,
    val imageUrl: String?
)