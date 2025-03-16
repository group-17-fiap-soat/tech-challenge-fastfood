package tech.challenge.fastfood.fastfood.adapters.gateways

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderItemMapper
import tech.challenge.fastfood.fastfood.common.dtos.OrderItemDto
import tech.challenge.fastfood.fastfood.common.interfaces.datasource.OrderItemDataSource
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderItemGatewayInterface
import java.util.*

@Component
class OrderItemGateway(
    val orderItemDataSource: OrderItemDataSource
) : OrderItemGatewayInterface {
    override fun save(entity: OrderItemDto): tech.challenge.fastfood.fastfood.common.daos.OrderItemDAO {
        return orderItemDataSource.save(OrderItemMapper.toEntity(entity))
    }

    override fun saveAll(entityList: List<OrderItemDto>): List<tech.challenge.fastfood.fastfood.common.daos.OrderItemDAO> {
        return orderItemDataSource.saveAll(entityList.map(OrderItemMapper::toEntity))
    }

    override fun findAllByOrderId(id: UUID): List<tech.challenge.fastfood.fastfood.common.daos.OrderItemDAO> {
        return orderItemDataSource.findAllByOrderId(id)
    }


}