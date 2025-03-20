package tech.challenge.fastfood.fastfood.usecases.product

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.ProductGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Product


@Service
class PutProductUseCase(
    private val productGatewayInterface: ProductGatewayInterface
) {

    fun execute(product: Product): Product {
        requireNotNull(product.id) { "O id do produto não pode ser nulo" }
        val entityToUpdate = productGatewayInterface.findById(product.id!!)
        val updatedEntity = entityToUpdate?.let {
            productGatewayInterface.save(product)
        } ?: throw EntityNotFoundException("A entidade com o id fornecido não existe, revise o corpo da requisição")
        return updatedEntity
    }
}