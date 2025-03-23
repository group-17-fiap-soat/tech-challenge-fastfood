package tech.challenge.fastfood.fastfood.common.interfaces.gateway

import tech.challenge.fastfood.fastfood.entities.Order
import java.util.*

interface OrderGatewayInterface {
    fun findAll(): List<Order>
    fun findById(id: UUID): Order?
    fun save(entity: Order): Order
}