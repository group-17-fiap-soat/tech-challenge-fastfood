package tech.challenge.fastfood.fastfood.infra.adapters.controllers.response

data class ErrorResponseV1 (
    val message: String,
    val origin: String,
    val status: String,
)