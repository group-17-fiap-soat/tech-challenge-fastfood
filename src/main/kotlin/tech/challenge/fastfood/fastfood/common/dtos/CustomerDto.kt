package tech.challenge.fastfood.fastfood.common.dtos

import java.time.OffsetDateTime
import java.util.UUID

data class CustomerDto(
    val id: UUID? = null,
    val cpf: String? = null,
    val name: String? = null,
    val email: String? = null,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null
)
