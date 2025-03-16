package tech.challenge.fastfood.fastfood.usecases.product

import jakarta.persistence.EntityNotFoundException
import tech.challenge.fastfood.fastfood.common.dtos.ProductDto
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.ProductGatewayInterface
import tech.challenge.fastfood.fastfood.adapters.presenters.ProductMapper

class PutProductUseCase(
    private val productGatewayInterface: ProductGatewayInterface
) {

    fun execute(productDto: ProductDto): ProductDto {
        requireNotNull(productDto.id) { "O id do produto não pode ser nulo" }
        val entityToUpdate = productGatewayInterface.findById(productDto.id)
        val updatedEntity = entityToUpdate?.let {
            productGatewayInterface.save(productDto)
        } ?: throw EntityNotFoundException("A entidade com o id fornecido não existe, revise o corpo da requisição")
        return ProductMapper.toDto(updatedEntity)
    }
}