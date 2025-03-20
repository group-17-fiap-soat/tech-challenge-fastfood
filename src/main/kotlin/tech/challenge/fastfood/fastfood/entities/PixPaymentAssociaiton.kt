package tech.challenge.fastfood.fastfood.entities

data class PixPaymentAssociaiton(
    val paymentData: PaymentData,
    val qrCode: String ,
    val qrCodeImage : String,
)
