package tech.challenge.fastfood.fastfood.common.interfaces.gateway

import tech.challenge.fastfood.fastfood.entities.OrderItem
import java.util.*

interface OrderItemGatewayInterface {
    fun save(entity: OrderItem): OrderItem
    fun saveAll(entityList: List<OrderItem>): List<OrderItem>
    fun findAllByOrderId(id: UUID): List<OrderItem>
}