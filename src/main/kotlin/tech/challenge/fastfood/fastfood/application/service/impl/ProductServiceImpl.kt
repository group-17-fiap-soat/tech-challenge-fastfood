package tech.challenge.fastfood.fastfood.application.service.impl

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.application.dto.ProductDto
import tech.challenge.fastfood.fastfood.application.service.ProductService
import tech.challenge.fastfood.fastfood.domain.port.ProductRepositoryPort
import tech.challenge.fastfood.fastfood.infra.mapper.ProductMapper
import java.util.*

@Service
class ProductServiceImpl(
    private val productRepositoryPort: ProductRepositoryPort
) : ProductService {
    override fun createProduct(productDto: ProductDto): ProductDto {
        return ProductMapper.toDto(productRepositoryPort.save(productDto))
    }

    override fun putProduct(productDto: ProductDto): ProductDto {
        val upsertProductEntity = if (productDto.id == null) {
            productDto.copy(
                id = UUID.randomUUID()
            ).let(productRepositoryPort::save)
        } else {
            productRepositoryPort.save(productDto)
        }

        return ProductMapper.toDto(upsertProductEntity)
    }

    override fun findAllByCategory(category: String): List<ProductDto> {
        return productRepositoryPort.findAllByCategory(category).map(ProductMapper::toDto)
    }

    override fun removeProductById(id: UUID): Boolean {
       return  productRepositoryPort.delete(id).let{ true }
    }
}