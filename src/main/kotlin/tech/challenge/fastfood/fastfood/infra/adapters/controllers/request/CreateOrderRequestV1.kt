package tech.challenge.fastfood.fastfood.infra.adapters.controllers.request

import tech.challenge.fastfood.fastfood.domain.model.StatusOrderEntity
import java.util.*

data class CreateOrderRequestV1(
    val idCustomer: UUID? = null,
    val orderItems: List<CreateOrderItemRequestV1> = listOf()
)
