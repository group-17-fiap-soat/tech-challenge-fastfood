package tech.challenge.fastfood.fastfood.infra.adapters.controllers.request

import java.util.*

data class CreateOrderRequestV1(
    val customerId: UUID? = null,
    val orderItems: List<CreateOrderItemRequestV1> = listOf()
)
