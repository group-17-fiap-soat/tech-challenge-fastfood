package tech.challenge.fastfood.fastfood.usecases.customer

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.CustomerGatewayInterface
import tech.challenge.fastfood.fastfood.common.utils.Validator
import tech.challenge.fastfood.fastfood.entities.Customer

@Service
class CreateCustomerUseCase(
    private val customerGatewayInterface: CustomerGatewayInterface
) {
    fun execute(customer: Customer): Customer {
        validateCustomer(customer)

        return customerGatewayInterface.save(customer)
    }

    private fun isCpfAlreadyRegistered(cpf: String): Boolean {
        return customerGatewayInterface.findByCpf(cpf) != null
    }

    private fun isEmailAlreadyRegistered(email: String): Boolean {
        return customerGatewayInterface.findByEmail(email) != null
    }

    private fun validateCustomer(customer: Customer) {
        val cpf = checkNotNull(customer.cpf) {
            throw InvalidCustomerDataException("CPF tem que ser preenchido.")
        }
        if (customer.email!= null && !Validator.isValidEmail(customer.email))
            throw InvalidCustomerDataException("Email inválido.")

        if (!Validator.isValidCpf(cpf))
            throw InvalidCustomerDataException("CPF inválido.")

        if (isCpfAlreadyRegistered(cpf))
            throw InvalidCustomerDataException("CPF já cadastrado.")

        if (isEmailAlreadyRegistered(customer.email!!))
            throw InvalidCustomerDataException("Email já cadastrado.")
    }
}