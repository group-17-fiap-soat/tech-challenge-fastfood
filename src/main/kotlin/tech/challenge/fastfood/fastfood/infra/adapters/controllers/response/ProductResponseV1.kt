package tech.challenge.fastfood.fastfood.infra.adapters.controllers.response

import java.math.BigDecimal
import java.util.*

data class ProductResponseV1(
    val id: UUID?,
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val category: String,
    val imageUrl: String?
)