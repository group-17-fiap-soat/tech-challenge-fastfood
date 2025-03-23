package tech.challenge.fastfood.fastfood.common.dto.request

import java.math.BigDecimal

data class CreatePaymentRequestV1(
    val value: BigDecimal,
    val email: String
)