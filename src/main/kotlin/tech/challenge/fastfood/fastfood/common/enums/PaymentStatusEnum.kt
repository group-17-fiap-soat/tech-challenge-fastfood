package tech.challenge.fastfood.fastfood.common.enums

enum class PaymentStatusEnum(status: String) {
    PAYMENT_APPROVED("approved"),
    PAYMENT_PENDING("pending"),
    PAYMENT_ERROR("denied")
}