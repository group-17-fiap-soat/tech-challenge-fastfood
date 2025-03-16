package tech.challenge.fastfood.fastfood.common.interfaces.gateway

import tech.challenge.fastfood.fastfood.common.dtos.ProductDto
import tech.challenge.fastfood.fastfood.common.daos.ProductDAO
import java.util.*

interface ProductGatewayInterface {
    fun save(productDto: tech.challenge.fastfood.fastfood.common.dtos.ProductDto): tech.challenge.fastfood.fastfood.common.daos.ProductDAO
    fun findByOrderItemId(id: UUID): tech.challenge.fastfood.fastfood.common.daos.ProductDAO?
    fun findAll(): List<tech.challenge.fastfood.fastfood.common.daos.ProductDAO>
    fun findAllByCategory(category: String): List<tech.challenge.fastfood.fastfood.common.daos.ProductDAO>
    fun findById(id: UUID): tech.challenge.fastfood.fastfood.common.daos.ProductDAO?
    fun delete(id: UUID)
}