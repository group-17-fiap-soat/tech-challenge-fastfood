package tech.challenge.fastfood.fastfood.infra.adapters.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.domain.model.ProductEntity
import java.util.*

@Repository
interface ProductJPAInterface : JpaRepository<ProductEntity, UUID> {
    fun findAllByCategory(category: String): List<ProductEntity>

    @Query("""
    SELECT p.* 
    FROM tb_order_item oi 
    JOIN tb_product p ON oi.product_id = p.id 
    WHERE oi.id = :id
""", nativeQuery = true)
    fun findByOrderItemId(id: UUID): Optional<ProductEntity>
}