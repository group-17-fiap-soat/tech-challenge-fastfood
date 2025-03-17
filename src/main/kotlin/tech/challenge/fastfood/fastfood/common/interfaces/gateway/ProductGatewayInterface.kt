package tech.challenge.fastfood.fastfood.common.interfaces.gateway

import tech.challenge.fastfood.fastfood.common.daos.ProductDAO
import tech.challenge.fastfood.fastfood.common.dtos.ProductDto
import java.util.*

interface ProductGatewayInterface {
    fun save(productDto: ProductDto): ProductDAO
    fun findByOrderItemId(id: UUID): ProductDAO?
    fun findAll(): List<ProductDAO>
    fun findAllByCategory(category: String): List<ProductDAO>
    fun findById(id: UUID): ProductDAO?
    fun delete(id: UUID)
}