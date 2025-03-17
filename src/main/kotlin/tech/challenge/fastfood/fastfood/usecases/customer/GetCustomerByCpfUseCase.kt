package tech.challenge.fastfood.fastfood.usecases.customer

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.adapters.presenters.CustomerMapper
import tech.challenge.fastfood.fastfood.common.dtos.CustomerDto
import tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.CustomerGatewayInterface
import tech.challenge.fastfood.fastfood.common.utils.Validator

@Service
class GetCustomerByCpfUseCase(
    private val customerGatewayInterface: CustomerGatewayInterface
) {

    fun execute(cpf: String): CustomerDto? {
        if (!Validator.isValidCpf(cpf)) {
            throw InvalidCustomerDataException("CPF inválido.")
        }
        return customerGatewayInterface.findByCpf(cpf)?.let(CustomerMapper::toDto)
    }
}