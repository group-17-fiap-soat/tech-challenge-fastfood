package tech.challenge.fastfood.fastfood.infra.adapters

import org.hibernate.Hibernate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import tech.challenge.fastfood.fastfood.application.dto.OrderItemDto
import tech.challenge.fastfood.fastfood.domain.model.OrderItemEntity
import tech.challenge.fastfood.fastfood.domain.port.OrderItemRepositoryPort
import tech.challenge.fastfood.fastfood.infra.adapters.repositories.OrderItemJPAInterface
import tech.challenge.fastfood.fastfood.infra.mapper.OrderItemMapper
import java.util.*

@Component
class OrderItemAdapter(
    val orderItemJPAInterface: OrderItemJPAInterface
) : OrderItemRepositoryPort {
    override fun save(entity: OrderItemDto): OrderItemEntity {
        return orderItemJPAInterface.save(OrderItemMapper.toEntity(entity))
    }

    override fun saveAll(entityList: List<OrderItemDto>): List<OrderItemEntity> {
        return orderItemJPAInterface.saveAll(entityList.map(OrderItemMapper::toEntity))
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun saveAllAndFetchData(entityList: List<OrderItemDto>): List<OrderItemEntity> {
        val savedItems = orderItemJPAInterface.saveAll(entityList.map(OrderItemMapper::toEntity))

        return orderItemJPAInterface.findAllByIdWithProduct(
            savedItems.map { it.id!! }
        )
    }

    override fun findAllByOrderId(id: UUID): List<OrderItemEntity> {
        return orderItemJPAInterface.findAllByOrderId(id)
    }


}