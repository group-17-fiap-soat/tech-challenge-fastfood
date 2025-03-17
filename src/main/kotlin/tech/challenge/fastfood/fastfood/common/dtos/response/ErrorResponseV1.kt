package tech.challenge.fastfood.fastfood.common.dtos.response

data class ErrorResponseV1 (
    val message: String,
    val origin: String,
    val status: String,
)