package tech.challenge.fastfood.fastfood.common.dto.request

import jakarta.validation.constraints.Pattern
import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum

data class ChangeOrderStatusRequestV1(
    var status: OrderStatusEnum? = null
)