package tech.challenge.fastfood.fastfood.infra.adapters.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.domain.model.ProductEntity
import java.util.UUID

@Repository
interface ProductJPAInterface: JpaRepository<ProductEntity, UUID> {
    fun findAllByCategory(category: String): List<ProductEntity>
}