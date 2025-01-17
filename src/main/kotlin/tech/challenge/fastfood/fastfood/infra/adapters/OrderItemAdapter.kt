package tech.challenge.fastfood.fastfood.infra.adapters

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.application.dto.OrderDto
import tech.challenge.fastfood.fastfood.application.dto.OrderItemDto
import tech.challenge.fastfood.fastfood.domain.model.OrderItemEntity
import tech.challenge.fastfood.fastfood.domain.port.OrderItemRepositoryPort
import tech.challenge.fastfood.fastfood.infra.adapters.repositories.OrderItemJPAInterface
import tech.challenge.fastfood.fastfood.infra.mapper.OrderItemMapper

@Component
class OrderItemAdapter(
   val orderItemJPAInterface: OrderItemJPAInterface
): OrderItemRepositoryPort {
    override fun save(entity: OrderItemDto): OrderItemEntity {
      return orderItemJPAInterface.save(OrderItemMapper.toEntity(entity))
    }

    override fun saveAll(entityList: List<OrderItemDto>): List<OrderItemEntity> {
        return orderItemJPAInterface.saveAll(entityList.map(OrderItemMapper::toEntity))
    }

}