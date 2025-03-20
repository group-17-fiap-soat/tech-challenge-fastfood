package tech.challenge.fastfood.fastfood.common.dto.request

data class CreateCustomerRequestV1(
    val cpf: String? = null,
    val name: String? = null,
    val email: String? = null
)
