package tech.challenge.fastfood.fastfood.common.interfaces.gateway

import tech.challenge.fastfood.fastfood.common.daos.OrderItemDAO
import tech.challenge.fastfood.fastfood.common.dtos.OrderItemDto
import java.util.*

interface OrderItemGatewayInterface {
    fun save(entity: OrderItemDto): OrderItemDAO
    fun saveAll(entityList: List<OrderItemDto>): List<OrderItemDAO>
    fun findAllByOrderId(id: UUID): List<OrderItemDAO>
}