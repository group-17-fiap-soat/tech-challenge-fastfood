package tech.challenge.fastfood.fastfood.infra.adapters.controllers.request

import java.math.BigDecimal

data class CreateProductRequestV1(
    val name: String,
    val description: String?,
    val price: BigDecimal,
    val category: String,
    val imageUrl: String?
)