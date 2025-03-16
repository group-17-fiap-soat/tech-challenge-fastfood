package tech.challenge.fastfood.fastfood.usecases.customer

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.adapters.presenters.CustomerMapper
import tech.challenge.fastfood.fastfood.common.dtos.CustomerDto
import tech.challenge.fastfood.fastfood.common.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.CustomerGatewayInterface
import tech.challenge.fastfood.fastfood.common.utils.Validator

@Service
class CreateCustomerUseCase(
    private val customerGatewayInterface: CustomerGatewayInterface
) {
    fun execute(customerDto: CustomerDto): CustomerDto {
        val customer = validateCustomer(customerDto)

        return CustomerMapper.toDto(customerGatewayInterface.save(customer))
    }

    private fun isCpfAlreadyRegistered(cpf: String): Boolean {
        return customerGatewayInterface.findByCpf(cpf) != null
    }

    private fun isEmailAlreadyRegistered(email: String): Boolean {
        return customerGatewayInterface.findByEmail(email) != null
    }

    private fun validateCustomer(customerDto: CustomerDto): CustomerDto {
        val cpf = checkNotNull(customerDto.cpf) {
            throw InvalidCustomerDataException("CPF tem que ser preenchido.")
        }
        if (customerDto.email!= null && !Validator.isValidEmail(customerDto.email))
            throw InvalidCustomerDataException("Email inválido.")

        if (!Validator.isValidCpf(cpf))
            throw InvalidCustomerDataException("CPF inválido.")

        if (isCpfAlreadyRegistered(cpf))
            throw InvalidCustomerDataException("CPF já cadastrado.")

        if (isEmailAlreadyRegistered(customerDto.email!!))
            throw InvalidCustomerDataException("Email já cadastrado.")

        return customerDto
    }
}