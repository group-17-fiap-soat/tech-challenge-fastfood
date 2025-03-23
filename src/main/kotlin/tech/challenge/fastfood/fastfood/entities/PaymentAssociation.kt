package tech.challenge.fastfood.fastfood.entities

import java.util.*

data class PaymentAssociation(
    val paymentData: PaymentData? = null,
    val data: Map<String, Any>? = null
)
