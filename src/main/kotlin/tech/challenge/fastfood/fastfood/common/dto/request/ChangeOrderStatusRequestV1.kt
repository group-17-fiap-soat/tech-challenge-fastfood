package tech.challenge.fastfood.fastfood.common.dto.request

import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum

data class ChangeOrderStatusRequestV1(
    var status: OrderStatusEnum? = null
)