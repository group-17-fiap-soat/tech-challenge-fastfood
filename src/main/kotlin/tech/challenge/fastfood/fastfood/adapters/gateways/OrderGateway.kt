package tech.challenge.fastfood.fastfood.adapters.gateways

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.presenters.OrderMapper
import tech.challenge.fastfood.fastfood.common.daos.OrderDAO
import tech.challenge.fastfood.fastfood.common.dtos.OrderDto
import tech.challenge.fastfood.fastfood.common.interfaces.datasource.OrderDataSource
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import java.util.*

@Component
class OrderGateway(
    val orderDataSource: OrderDataSource
) : OrderGatewayInterface {

    override fun findAll(): List<OrderDAO> {
        return orderDataSource.findAll()
    }

    override fun findById(id: UUID): OrderDAO? {
        return orderDataSource.findById(id).orElse(null)
    }

    override fun save(entity: OrderDto): OrderDAO {
        val orderEntity = OrderMapper.toEntity(entity)
            .copy(status = tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum.RECEIVED)

        return orderDataSource.save(orderEntity)
    }

}