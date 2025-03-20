package tech.challenge.fastfood.fastfood.adapters.gateways

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderItemMapper
import tech.challenge.fastfood.fastfood.common.daos.OrderItemDAO
import tech.challenge.fastfood.fastfood.common.dtos.OrderItemDto
import tech.challenge.fastfood.fastfood.common.interfaces.datasource.OrderItemDataSource
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderItemGatewayInterface
import tech.challenge.fastfood.fastfood.entities.OrderItem
import java.util.*

@Component
class OrderItemGateway(
    val orderItemDataSource: OrderItemDataSource
) : OrderItemGatewayInterface {
    override fun save(entity: OrderItem): OrderItem {
        return OrderItemMapper.toDao(entity).let(orderItemDataSource::save).let(OrderItemMapper::toEntity)
    }

    override fun saveAll(entityList: List<OrderItem>): List<OrderItem> {
        return orderItemDataSource.saveAll(entityList.map(OrderItemMapper::toDao)).map(OrderItemMapper::toEntity)
    }

    override fun findAllByOrderId(id: UUID): List<OrderItem> {
        return orderItemDataSource.findAllByOrderId(id).map(OrderItemMapper::toEntity)
    }

}