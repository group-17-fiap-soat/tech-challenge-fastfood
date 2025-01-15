package tech.challenge.fastfood.fastfood.infra.adapters.controllers.response

import tech.challenge.fastfood.fastfood.domain.model.StatusOrderEntity
import java.util.UUID

data class OrderResponseV1(
    val orderNumber: Long,
    val customer: CustomerResponseV1,
    val status: StatusOrderEntity
)
