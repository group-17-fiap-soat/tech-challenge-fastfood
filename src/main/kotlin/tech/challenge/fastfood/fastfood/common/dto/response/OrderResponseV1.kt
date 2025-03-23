package tech.challenge.fastfood.fastfood.common.dto.response

import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum
import java.math.BigDecimal
import java.util.*

data class OrderResponseV1(
    val id: UUID? = null,
    val payment: PaymentResponseV1? = null,
    val orderNumber: Long? = null,
    val customerId: UUID? = null,
    val orderItems: List<OrderItemResponseV1>? = null,
    val status: OrderStatusEnum? = null,
    val totalPrice: BigDecimal? = null,
)
