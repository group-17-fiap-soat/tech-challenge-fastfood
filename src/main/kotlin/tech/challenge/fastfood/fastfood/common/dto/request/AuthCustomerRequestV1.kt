package tech.challenge.fastfood.fastfood.common.dto.request

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class AuthCustomerRequestV1(
    @field:NotNull(message = "O CPF não pode estar vazio.")
    @field:Pattern(regexp = "^[0-9]{11}\$")
    var cpf: String? = null
)
