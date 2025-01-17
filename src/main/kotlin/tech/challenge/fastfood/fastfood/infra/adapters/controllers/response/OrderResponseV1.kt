package tech.challenge.fastfood.fastfood.infra.adapters.controllers.response

import tech.challenge.fastfood.fastfood.domain.model.StatusOrderEntity
import java.math.BigDecimal
import java.util.UUID

data class OrderResponseV1(
    val id: UUID? = null,
    val orderNumber: Long? = null,
    val customerId: UUID? = null,
    val orderItems: List<OrderItemResponseV1>? = null,
    val status: StatusOrderEntity? = null,
    val totalPrice: BigDecimal? = null
)
