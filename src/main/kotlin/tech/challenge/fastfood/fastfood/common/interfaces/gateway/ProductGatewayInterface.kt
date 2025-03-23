package tech.challenge.fastfood.fastfood.common.interfaces.gateway

import tech.challenge.fastfood.fastfood.entities.Product
import java.util.*

interface ProductGatewayInterface {
    fun save(product: Product): Product
    fun findByOrderItemId(id: UUID): Product?
    fun findAll(): List<Product>
    fun findAllByCategory(category: String): List<Product>
    fun findById(id: UUID): Product?
    fun delete(id: UUID)
}