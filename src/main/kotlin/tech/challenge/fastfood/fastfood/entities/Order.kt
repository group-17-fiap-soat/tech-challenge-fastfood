package tech.challenge.fastfood.fastfood.entities

import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

data class Order(
    val id: UUID? = null,
    var orderNumber: Long? = null,
    val customerId: UUID? = null,
    val status: OrderStatusEnum? = null,
    val orderDate: OffsetDateTime? = null,
    val finishedDate: OffsetDateTime? = null,
    var createdAt: OffsetDateTime? = null,
    var updatedAt: OffsetDateTime? = null,
    val orderItems: List<OrderItem> = listOf(),
    val totalPrice: BigDecimal = calculateTotalPrice(orderItems)
)

fun calculateTotalPrice(orderItems: List<OrderItem>): BigDecimal {
    return orderItems.fold(BigDecimal.ZERO) { acc, item ->
        acc + item.product?.price!!.multiply(item.quantity?.toBigDecimal())
    }
}



