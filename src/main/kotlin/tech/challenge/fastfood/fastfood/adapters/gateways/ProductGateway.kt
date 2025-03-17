package tech.challenge.fastfood.fastfood.adapters.gateways

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.presenters.ProductMapper
import tech.challenge.fastfood.fastfood.common.daos.ProductDAO
import tech.challenge.fastfood.fastfood.common.dtos.ProductDto
import tech.challenge.fastfood.fastfood.common.interfaces.datasource.ProductDataSource
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.ProductGatewayInterface
import java.util.*

@Component
class ProductGateway(
    val productDataSource: ProductDataSource
): ProductGatewayInterface {
    override fun save(productDto: ProductDto): ProductDAO {
        return ProductMapper.toEntity(productDto).let(productDataSource::save)
    }

    override fun findAllByCategory(category: String): List<ProductDAO> {
        return productDataSource.findAllByCategory(category)
    }

    override fun findById(id: UUID): ProductDAO? {
        return productDataSource.findById(id).orElse(null)
    }

    override fun findByOrderItemId(id: UUID): ProductDAO? {
        return productDataSource.findByOrderItemId(id).orElse(null)
    }

    override fun findAll(): List<ProductDAO> {
        return productDataSource.findAll()
    }

    override fun delete(id: UUID) {
       return productDataSource.deleteById(id);
    }

}