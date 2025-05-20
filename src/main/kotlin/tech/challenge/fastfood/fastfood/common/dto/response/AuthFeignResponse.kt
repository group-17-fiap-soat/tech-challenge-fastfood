package tech.challenge.fastfood.fastfood.common.dto.response

import java.util.UUID

data class AuthFeignResponse(
    val id: UUID? = null,
    val email: String? = null,
    val name: String? = null,
    val token: String? = null
)
