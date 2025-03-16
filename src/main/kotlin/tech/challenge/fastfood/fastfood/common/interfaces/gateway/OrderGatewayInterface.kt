package tech.challenge.fastfood.fastfood.common.interfaces.gateway

import tech.challenge.fastfood.fastfood.common.dtos.OrderDto
import tech.challenge.fastfood.fastfood.common.daos.OrderDAO
import java.util.*

interface OrderGatewayInterface {
    fun findAll(): List<OrderDAO>
    fun findById(id: UUID): OrderDAO?
    fun save(entity: OrderDto): OrderDAO
}