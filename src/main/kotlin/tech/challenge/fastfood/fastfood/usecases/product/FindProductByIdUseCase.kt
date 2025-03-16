package tech.challenge.fastfood.fastfood.usecases.product

import tech.challenge.fastfood.fastfood.common.dtos.ProductDto
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.ProductGatewayInterface
import tech.challenge.fastfood.fastfood.adapters.presenters.ProductMapper
import java.util.*

class FindProductByIdUseCase(
    private val productGatewayInterface: ProductGatewayInterface
) {

    fun execute(id: UUID): ProductDto? {
        return productGatewayInterface.findById(id)?.let(ProductMapper::toDto)
    }
}