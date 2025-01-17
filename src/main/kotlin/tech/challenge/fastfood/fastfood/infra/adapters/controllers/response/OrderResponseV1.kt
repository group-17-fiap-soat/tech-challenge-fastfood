package tech.challenge.fastfood.fastfood.infra.adapters.controllers.response

import tech.challenge.fastfood.fastfood.domain.model.StatusOrderEntity
import java.util.UUID

data class OrderResponseV1(
    val orderNumber: Long? = null,
    val idCustomer: UUID? = null,
    val orderItems: List<UUID?>? = null,
    val status: StatusOrderEntity? = null,
)
