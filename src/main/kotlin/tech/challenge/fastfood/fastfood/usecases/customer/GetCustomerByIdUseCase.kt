package tech.challenge.fastfood.fastfood.usecases.customer

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.CustomerGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Customer
import java.util.*

@Service
class GetCustomerByIdUseCase(
    private val customerGatewayInterface: CustomerGatewayInterface
) {

    fun execute(id: UUID): Customer? {
        return customerGatewayInterface.findById(id)
    }
}