package tech.challenge.fastfood.fastfood.common.dto.response

import tech.challenge.fastfood.fastfood.common.enums.CategoryEnum
import java.math.BigDecimal
import java.util.*

data class OrderItemResponseV1(
    val id: UUID? = null,
    val productId: UUID? = null,
    val category: CategoryEnum? = null,
    val quantity: Int? = null,
    val price: BigDecimal? = null
)