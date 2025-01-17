package tech.challenge.fastfood.fastfood.infra.adapters.controllers.request

import java.util.UUID

data class CreateOrderItemRequestV1(
    val productId: UUID,
    val quantity: Int
)
