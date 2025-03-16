package tech.challenge.fastfood.fastfood.common.interfaces.gateway

import tech.challenge.fastfood.fastfood.common.dtos.OrderItemDto
import tech.challenge.fastfood.fastfood.common.daos.OrderItemDAO
import java.util.UUID

interface OrderItemGatewayInterface {
    fun save(entity: tech.challenge.fastfood.fastfood.common.dtos.OrderItemDto): tech.challenge.fastfood.fastfood.common.daos.OrderItemDAO
    fun saveAll(entityList: List<tech.challenge.fastfood.fastfood.common.dtos.OrderItemDto>): List<tech.challenge.fastfood.fastfood.common.daos.OrderItemDAO>
    fun findAllByOrderId(id: UUID): List<tech.challenge.fastfood.fastfood.common.daos.OrderItemDAO>
}