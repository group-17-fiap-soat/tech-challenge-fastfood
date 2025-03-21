package tech.challenge.fastfood.fastfood.common.enums

enum class OrderStatusEnum(val priority: Int?) {
    PENDING_AUTHORIZATION(null),
    READY(1),
    IN_PROGRESS(2),
    RECEIVED(3),
    FINISHED(0)
}