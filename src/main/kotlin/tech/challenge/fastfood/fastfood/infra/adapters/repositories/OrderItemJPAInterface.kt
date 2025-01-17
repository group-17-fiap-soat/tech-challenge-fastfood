package tech.challenge.fastfood.fastfood.infra.adapters.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.domain.model.OrderItemEntity
import java.util.*

@Repository
interface OrderItemJPAInterface : JpaRepository<OrderItemEntity, UUID> {
    fun findAllByOrderId(id: UUID): List<OrderItemEntity>
}