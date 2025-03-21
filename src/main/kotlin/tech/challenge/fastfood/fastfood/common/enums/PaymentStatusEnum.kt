package tech.challenge.fastfood.fastfood.common.enums


enum class PaymentStatusEnum(val status: String) {
    PAYMENT_APPROVED("approved"),
    PAYMENT_PENDING("pending"),
    PAYMENT_ERROR("denied");

    companion object {
        fun getByStatus(status: String): PaymentStatusEnum {
            return entries.firstOrNull { it.status.equals(status, ignoreCase = true) }
                ?: throw IllegalArgumentException("No enum constant with status: $status")
        }
    }
}