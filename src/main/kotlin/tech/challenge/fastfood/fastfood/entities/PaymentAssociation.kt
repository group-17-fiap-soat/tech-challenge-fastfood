package tech.challenge.fastfood.fastfood.entities

import java.util.*

data class PaymentAssociation(
    val paymentData: PaymentData,
    val data: Map<String, Any>? = null
)
