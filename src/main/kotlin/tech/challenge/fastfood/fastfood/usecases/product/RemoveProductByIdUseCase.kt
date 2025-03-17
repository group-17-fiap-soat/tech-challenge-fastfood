package tech.challenge.fastfood.fastfood.usecases.product

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.ProductGatewayInterface
import java.util.*

@Service
class RemoveProductByIdUseCase(
    private val productGatewayInterface: ProductGatewayInterface
) {

    fun execute(id: UUID) {
        val entityToDelete = productGatewayInterface.findById(id)
        entityToDelete?.let { productGatewayInterface.delete(id) }
            ?: throw EntityNotFoundException("A entidade com o id fornecido não existe, revise o corpo da requisição")
    }
}