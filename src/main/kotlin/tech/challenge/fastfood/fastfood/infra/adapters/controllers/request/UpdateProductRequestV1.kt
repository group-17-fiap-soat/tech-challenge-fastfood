package tech.challenge.fastfood.fastfood.infra.adapters.controllers.request

import tech.challenge.fastfood.fastfood.domain.model.enums.CategoryEnum
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