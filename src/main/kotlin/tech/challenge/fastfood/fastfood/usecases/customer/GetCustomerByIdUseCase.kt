package tech.challenge.fastfood.fastfood.usecases.customer

import tech.challenge.fastfood.fastfood.common.dtos.CustomerDto
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.CustomerGatewayInterface
import tech.challenge.fastfood.fastfood.adapters.presenters.CustomerMapper
import java.util.*

class GetCustomerByIdUseCase(
    private val customerGatewayInterface: CustomerGatewayInterface
) {

    fun execute(id: UUID): CustomerDto? {
        return customerGatewayInterface.findById(id)?.let(CustomerMapper::toDto)
    }
}