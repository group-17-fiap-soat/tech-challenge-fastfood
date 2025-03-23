package tech.challenge.fastfood.fastfood.common.dto.response

data class PixPaymentResponseV1(
    val id: Long,
    val qrCode: String,
    val qrCodeImage: String,
    val status: String
)
