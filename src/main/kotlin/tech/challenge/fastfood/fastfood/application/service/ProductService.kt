package tech.challenge.fastfood.fastfood.application.service

import tech.challenge.fastfood.fastfood.application.dto.ProductDto
import java.util.*

interface ProductService {
    fun createProduct(productDto: ProductDto): ProductDto
    fun findById(id: UUID): ProductDto?
    fun putProduct(productDto: ProductDto): ProductDto
    fun findAllByCategory(category: String): List<ProductDto>
    fun removeProductById(id: UUID)
}