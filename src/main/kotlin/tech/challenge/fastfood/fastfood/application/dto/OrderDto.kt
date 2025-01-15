package tech.challenge.fastfood.fastfood.application.dto

import tech.challenge.fastfood.fastfood.domain.model.CustomerEntity
import tech.challenge.fastfood.fastfood.domain.model.StatusOrderEntity
import java.time.OffsetDateTime
import java.util.*

data class OrderDto(
    val id: UUID? = null,
    val orderNumber: Long,
    val customer: CustomerDto? = null,
    val status: StatusOrderEntity,
    val orderDate: OffsetDateTime? = null,
    val finishedAt: OffsetDateTime? = null,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null
)
