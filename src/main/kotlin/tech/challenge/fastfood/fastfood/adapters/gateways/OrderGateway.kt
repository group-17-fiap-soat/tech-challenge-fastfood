package tech.challenge.fastfood.fastfood.adapters.gateways

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderMapper
import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum
import tech.challenge.fastfood.fastfood.common.interfaces.datasource.OrderDataSource
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Order
import java.util.*

@Component
class OrderGateway(
    val orderDataSource: OrderDataSource
) : OrderGatewayInterface {

    override fun findAll(): List<Order> {
        return orderDataSource.findAll().map(OrderMapper::toEntity)
    }

    override fun findById(id: UUID): Order? {
        return OrderMapper.toEntity(orderDataSource.findById(id).orElse(null))
    }

    override fun save(entity: Order): Order {
        val orderEntity = OrderMapper.toDao(entity)
            .copy(status = OrderStatusEnum.RECEIVED)

        return OrderMapper.toEntity(orderDataSource.save(orderEntity))
    }

}