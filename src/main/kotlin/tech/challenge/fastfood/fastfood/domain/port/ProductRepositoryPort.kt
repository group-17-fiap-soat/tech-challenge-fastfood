package tech.challenge.fastfood.fastfood.domain.port

import tech.challenge.fastfood.fastfood.application.dto.ProductDto
import tech.challenge.fastfood.fastfood.domain.model.ProductEntity
import java.util.*

interface ProductRepositoryPort {
    fun save(productDto: ProductDto): ProductEntity
    fun findByOrderItemId(id: UUID): ProductEntity?
    fun findAll(): List<ProductEntity>
    fun findAllByCategory(category: String): List<ProductEntity>
    fun findById(id: UUID): ProductEntity?
    fun delete(id: UUID)
}