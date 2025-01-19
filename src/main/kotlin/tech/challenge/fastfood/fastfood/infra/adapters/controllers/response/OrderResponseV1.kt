package tech.challenge.fastfood.fastfood.infra.adapters.controllers.response

import tech.challenge.fastfood.fastfood.domain.model.enums.OrderStatusEnum
import java.math.BigDecimal
import java.util.UUID

data class OrderResponseV1(
    val id: UUID? = null,
    val orderNumber: Long? = null,
    val customerId: UUID? = null,
    val orderItems: List<OrderItemResponseV1>? = null,
    val status: OrderStatusEnum? = null,
    val totalPrice: BigDecimal? = null,
    val orderPaid: Boolean = true // FAKE CHECKOUT - o pedido sempre vai ser considerado como pago assim que for feito
)
