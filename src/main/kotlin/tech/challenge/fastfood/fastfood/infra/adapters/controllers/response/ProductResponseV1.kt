package tech.challenge.fastfood.fastfood.infra.adapters.controllers.response

import tech.challenge.fastfood.fastfood.domain.model.enums.CategoryEnum
import java.math.BigDecimal
import java.util.*

data class ProductResponseV1(
    val id: UUID?,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val category: CategoryEnum,
    val imageUrl: String?
)