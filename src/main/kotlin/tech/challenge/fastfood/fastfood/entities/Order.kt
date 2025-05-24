package tech.challenge.fastfood.fastfood.entities

import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

data class Order(
    var id: UUID? = null,
    var orderNumber: Long? = null,
    var customerId: UUID? = null,
    var payment: PaymentAssociation? = null,
    var status: OrderStatusEnum? = null,
    var orderDate: OffsetDateTime? = null,
    var finishedDate: OffsetDateTime? = null,
    var createdAt: OffsetDateTime? = null,
    var updatedAt: OffsetDateTime? = null,
    var orderItems: List<OrderItem> = listOf()
) {
    val totalPrice: BigDecimal
        get() = calculateTotalPrice()

    private fun calculateTotalPrice(): BigDecimal {
        return orderItems.fold(BigDecimal.ZERO) { acc, item ->
            val itemPrice = item.product?.price ?: BigDecimal.ZERO
            val quantity = item.quantity?.toBigDecimal() ?: BigDecimal.ONE
            acc + itemPrice.multiply(quantity)
        }
    }
}



