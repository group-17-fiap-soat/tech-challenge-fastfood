package tech.challenge.fastfood.fastfood.common.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class CreateCustomerRequestV1(
    @field:Pattern(regexp = "^[0-9]{11}\$")
    val cpf: String? = null,

    val name: String? = null,

    @field:Email(message = "O e-mail precisa ser válido.")
    val email: String? = null
)
