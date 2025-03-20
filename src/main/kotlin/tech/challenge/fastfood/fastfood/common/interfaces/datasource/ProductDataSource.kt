package tech.challenge.fastfood.fastfood.common.interfaces.datasource

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.common.dao.ProductDAO
import java.util.*

@Repository
interface ProductDataSource : JpaRepository<ProductDAO, UUID> {
    fun findAllByCategory(category: String): List<ProductDAO>

    @Query("""
    SELECT p.* 
    FROM tb_order_item oi 
    JOIN tb_product p ON oi.product_id = p.id 
    WHERE oi.id = :id
""", nativeQuery = true)
    fun findByOrderItemId(id: UUID): Optional<ProductDAO>
}