package tech.challenge.fastfood.fastfood.infra.adapters.controllers.request

data class CreateCustomerRequestV1(
    val cpf: String? = null,
    val name: String? = null,
    val email: String? = null
)
