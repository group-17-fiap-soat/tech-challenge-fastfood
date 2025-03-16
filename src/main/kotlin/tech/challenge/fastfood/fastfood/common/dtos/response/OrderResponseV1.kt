package tech.challenge.fastfood.fastfood.common.dtos.response

import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum
import java.math.BigDecimal
import java.util.UUID

data class OrderResponseV1(
    val id: UUID? = null,
    val orderNumber: Long? = null,
    val customerId: UUID? = null,
    val orderItems: List<tech.challenge.fastfood.fastfood.common.dtos.response.OrderItemResponseV1>? = null,
    val status: tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum? = null,
    val totalPrice: BigDecimal? = null,
    val orderPaid: Boolean = true // FAKE CHECKOUT - o pedido sempre vai ser considerado como pago assim que for feito
)
