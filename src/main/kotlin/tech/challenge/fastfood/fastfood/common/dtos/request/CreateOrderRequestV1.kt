package tech.challenge.fastfood.fastfood.common.dtos.request

import java.util.*

data class CreateOrderRequestV1(
    val customerId: UUID? = null,
    val orderItems: List<CreateOrderItemRequestV1> = listOf()
)
