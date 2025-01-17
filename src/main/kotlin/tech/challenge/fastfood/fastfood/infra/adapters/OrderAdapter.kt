package tech.challenge.fastfood.fastfood.infra.adapters

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.application.dto.OrderDto
import tech.challenge.fastfood.fastfood.domain.model.OrderEntity
import tech.challenge.fastfood.fastfood.domain.model.StatusOrderEntity
import tech.challenge.fastfood.fastfood.domain.port.OrderRepositoryPort
import tech.challenge.fastfood.fastfood.infra.adapters.repositories.OrderJPAInterface
import tech.challenge.fastfood.fastfood.infra.mapper.OrderMapper
import java.util.*

@Component
class OrderAdapter(
    val orderJPAInterface: OrderJPAInterface
) : OrderRepositoryPort {

    override fun findAll(): List<OrderEntity> {
        return orderJPAInterface.findAll()
    }

    override fun findById(id: UUID): OrderEntity? {
        return orderJPAInterface.findById(id).orElse(null)
    }

    override fun save(entity: OrderDto): OrderEntity {
        val orderEntity = OrderMapper.toEntity(entity)
            .copy(status = StatusOrderEntity.RECEIVED)

        return orderJPAInterface.save(orderEntity)
    }

}