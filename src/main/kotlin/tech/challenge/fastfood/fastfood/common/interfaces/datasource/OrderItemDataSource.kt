package tech.challenge.fastfood.fastfood.common.interfaces.datasource

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.common.dao.OrderItemDAO
import java.util.*

@Repository
interface OrderItemDataSource : JpaRepository<OrderItemDAO, UUID> {
    fun findAllByOrderId(id: UUID): List<OrderItemDAO>

    @Query("""
    SELECT oi 
    FROM tb_order_item oi 
    INNER JOIN FETCH oi.product 
    WHERE oi.id IN :ids
    """)
    fun findAllByIdWithProduct(ids: List<UUID>): List<OrderItemDAO>
}