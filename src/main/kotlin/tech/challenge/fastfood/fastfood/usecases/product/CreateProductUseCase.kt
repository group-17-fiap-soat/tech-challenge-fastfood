package tech.challenge.fastfood.fastfood.usecases.product

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.ProductGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Product

@Service
class CreateProductUseCase(
    private val productGatewayInterface: ProductGatewayInterface
) {

    fun execute(product: Product): Product {
        return productGatewayInterface.save(product)
    }
}