package tech.challenge.fastfood.fastfood.adapters.gateways

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.presenters.ProductMapper
import tech.challenge.fastfood.fastfood.common.interfaces.datasource.ProductDataSource
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.ProductGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Product
import java.util.*

@Component
class ProductGateway(
    val productDataSource: ProductDataSource
): ProductGatewayInterface {
    override fun save(product: Product): Product {
        return ProductMapper.toDao(product).let(productDataSource::save).let(ProductMapper::toEntity)
    }

    override fun findAllByCategory(category: String): List<Product> {
        return productDataSource.findAllByCategory(category).map(ProductMapper::toEntity)
    }

    override fun findById(id: UUID): Product? {
        return ProductMapper.toEntity(productDataSource.findById(id).orElse(null))
    }

    override fun findByOrderItemId(id: UUID): Product? {
        return ProductMapper.toEntity(productDataSource.findByOrderItemId(id).orElse(null))
    }

    override fun findAll(): List<Product> {
        return productDataSource.findAll().map(ProductMapper::toEntity)
    }

    override fun delete(id: UUID) {
       return productDataSource.deleteById(id);
    }

}