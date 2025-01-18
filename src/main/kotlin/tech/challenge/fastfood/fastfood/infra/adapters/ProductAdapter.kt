package tech.challenge.fastfood.fastfood.infra.adapters

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.application.dto.ProductDto
import tech.challenge.fastfood.fastfood.domain.model.ProductEntity
import tech.challenge.fastfood.fastfood.domain.port.ProductRepositoryPort
import tech.challenge.fastfood.fastfood.infra.adapters.repositories.ProductJPAInterface
import tech.challenge.fastfood.fastfood.infra.mapper.ProductMapper
import java.util.*

@Component
class ProductAdapter(
    val productJPAInterface: ProductJPAInterface
): ProductRepositoryPort{
    override fun save(productDto: ProductDto): ProductEntity {
        return ProductMapper.toEntity(productDto).let(productJPAInterface::save)
    }

    override fun findAllByCategory(category: String): List<ProductEntity> {
        return productJPAInterface.findAllByCategory(category)
    }

    override fun findById(id: UUID): ProductEntity? {
        return productJPAInterface.findById(id).orElse(null)
    }

    override fun findByOrderItemId(id: UUID): ProductEntity? {
        return productJPAInterface.findByOrderItemId(id).orElse(null)
    }

    override fun findAll(): List<ProductEntity> {
        return productJPAInterface.findAll()
    }

    override fun delete(id: UUID) {
       return productJPAInterface.deleteById(id);
    }

}