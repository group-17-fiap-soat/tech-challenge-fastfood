package tech.challenge.fastfood.fastfood.application.service.impl

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
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
            requireNotNull(productDto.id){ "O id do produto não pode ser nulo" }
            val entityToUpdate = productRepositoryPort.findById(productDto.id)
           val updatedEntity =  entityToUpdate?.let{
                productRepositoryPort.save(productDto)
            } ?: throw EntityNotFoundException("A entidade com o id fornecido não existe, revise o corpo da requisição")
        return ProductMapper.toDto(updatedEntity)
    }

    override fun findAllByCategory(category: String): List<ProductDto> {
        return productRepositoryPort.findAllByCategory(category).map(ProductMapper::toDto)
    }

    override fun removeProductById(id: UUID) {
       val entityToDelete = productRepositoryPort.findById(id)
       entityToDelete?.let { productRepositoryPort.delete(id) } ?:
       throw EntityNotFoundException("A entidade com o id fornecido não existe, revise o corpo da requisição")
    }


}