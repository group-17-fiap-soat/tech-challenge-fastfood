package tech.challenge.fastfood.fastfood.common.dtos.request

import java.util.UUID

data class CreateOrderItemRequestV1(
    val productId: UUID,
    val quantity: Int
)
