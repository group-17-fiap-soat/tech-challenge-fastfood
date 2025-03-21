package tech.challenge.fastfood.fastfood.common.dto.response

data class ErrorResponseV1 (
    val message: String,
    val origin: String,
    val status: String,
)