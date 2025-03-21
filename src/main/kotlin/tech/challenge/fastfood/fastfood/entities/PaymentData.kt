package tech.challenge.fastfood.fastfood.entities

import tech.challenge.fastfood.fastfood.common.enums.PaymentStatusEnum
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

data class PaymentData(
    val id: UUID? = null,
    val externalId: Long? = null,
    val orderId: UUID?,
    val totalAmount: BigDecimal,
    val paymentMethod: String,
    val paymentStatus: PaymentStatusEnum,
    val paymentDate: OffsetDateTime? = null,
    var createdAt: OffsetDateTime? = null,
    var updatedAt: OffsetDateTime? = null
)
