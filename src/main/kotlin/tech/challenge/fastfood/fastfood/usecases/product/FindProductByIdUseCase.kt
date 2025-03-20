package tech.challenge.fastfood.fastfood.usecases.product

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.dtos.ProductDto
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.ProductGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Product
import java.util.*

@Service
class FindProductByIdUseCase(
    private val productGatewayInterface: ProductGatewayInterface
) {

    fun execute(id: UUID): Product? {
        return productGatewayInterface.findById(id)
    }
}