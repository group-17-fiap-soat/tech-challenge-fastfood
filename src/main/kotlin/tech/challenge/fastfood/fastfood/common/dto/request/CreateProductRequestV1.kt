package tech.challenge.fastfood.fastfood.common.dto.request

import tech.challenge.fastfood.fastfood.common.enums.CategoryEnum
import java.math.BigDecimal

data class CreateProductRequestV1(
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val category: CategoryEnum,
    val imageUrl: String?
)