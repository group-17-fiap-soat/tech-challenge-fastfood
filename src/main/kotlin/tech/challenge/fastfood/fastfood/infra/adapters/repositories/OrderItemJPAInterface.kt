package tech.challenge.fastfood.fastfood.infra.adapters.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import tech.challenge.fastfood.fastfood.domain.model.OrderItemEntity
import java.util.*

@Repository
interface OrderItemJPAInterface : JpaRepository<OrderItemEntity, UUID> {
    fun findAllByOrderId(id: UUID): List<OrderItemEntity>

    @Query("""
    SELECT oi 
    FROM tb_order_item oi 
    INNER JOIN FETCH oi.product 
    WHERE oi.id IN :ids
    """)
    fun findAllByIdWithProduct(ids: List<UUID>): List<OrderItemEntity>
}