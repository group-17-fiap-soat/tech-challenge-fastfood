package tech.challenge.fastfood.fastfood.common.dtos.response

import tech.challenge.fastfood.fastfood.common.enums.CategoryEnum
import java.math.BigDecimal
import java.util.*

data class ProductResponseV1(
    val id: UUID?,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val category: tech.challenge.fastfood.fastfood.common.enums.CategoryEnum,
    val imageUrl: String?
)