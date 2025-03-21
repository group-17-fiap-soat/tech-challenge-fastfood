package tech.challenge.fastfood.fastfood.common.dto.response

import java.util.UUID

data class PaymentResponseV1(
    val id: UUID? = null,
    val externalId: Long? = null,
    val data: Map<String, Any>? = null
)
