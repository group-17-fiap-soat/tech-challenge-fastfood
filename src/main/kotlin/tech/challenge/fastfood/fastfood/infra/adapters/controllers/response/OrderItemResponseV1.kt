package tech.challenge.fastfood.fastfood.infra.adapters.controllers.response

import java.math.BigDecimal
import java.util.*

data class OrderItemResponseV1(
    val id: UUID? = null,
    val productId: UUID? = null ,
    val quantity: Int? = null,
    val price: BigDecimal? = null
)