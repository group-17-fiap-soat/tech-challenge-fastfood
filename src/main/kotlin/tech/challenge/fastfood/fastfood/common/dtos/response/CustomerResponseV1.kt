package tech.challenge.fastfood.fastfood.common.dtos.response

import java.util.UUID

data class CustomerResponseV1(
    val id: UUID? = null,
    val cpf: String? = null,
    val name: String? = null,
    val email: String? = null
)
