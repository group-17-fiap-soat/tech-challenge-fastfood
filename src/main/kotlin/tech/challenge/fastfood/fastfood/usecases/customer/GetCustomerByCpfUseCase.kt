package tech.challenge.fastfood.fastfood.usecases.customer

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.adapters.gateways.AuthFeignGateway
import tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.common.utils.Validator
import tech.challenge.fastfood.fastfood.entities.Customer

@Service
class GetCustomerByCpfUseCase(
    private val authFeignGateway: AuthFeignGateway
) {

    fun execute(cpf: String): Customer? {
        if (!Validator.isValidCpf(cpf)) {
            throw InvalidCustomerDataException("CPF inválido.")
        }
        return authFeignGateway.getCustomerByCpf(cpf)
    }
}