package tech.challenge.fastfood.fastfood.application.service.impl

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.application.dto.CustomerDto
import tech.challenge.fastfood.fastfood.application.service.CustomerService
import tech.challenge.fastfood.fastfood.domain.port.CustomerRepositoryPort
import tech.challenge.fastfood.fastfood.infra.exception.InvalidCustomerDataException
import tech.challenge.fastfood.fastfood.infra.mapper.CustomerMapper
import java.util.*

@Service
class CustomerServiceImpl(
    private val customerRepositoryPort: CustomerRepositoryPort
) : CustomerService {

    override fun createCustomer(customerDto: CustomerDto): CustomerDto {
        val customer = validateCustomer(customerDto)

        return CustomerMapper.toDto(customerRepositoryPort.save(customer))
    }

    override fun getCustomerByCpf(cpf: String): CustomerDto? {
        if (!isValidCpf(cpf)) {
            throw InvalidCustomerDataException("CPF inválido.")
        }
        return customerRepositoryPort.findByCpf(cpf)?.let(CustomerMapper::toDto)
    }


    override fun getCustomerById(id: UUID): CustomerDto? {
        return customerRepositoryPort.findById(id)?.let(CustomerMapper::toDto)
    }

    private fun isValidCpf(cpf: String) = cpf.matches(Regex("^[0-9]{11}$"))


    private fun isCpfAlreadyRegistered(cpf: String): Boolean {
        return customerRepositoryPort.findByCpf(cpf) != null
    }

    private fun isEmailAlreadyRegistered(email: String): Boolean {
        return customerRepositoryPort.findByEmail(email) != null
    }

    private fun validateCustomer(customerDto: CustomerDto): CustomerDto {
        val cpf = checkNotNull(customerDto.cpf) {
            throw InvalidCustomerDataException("CPF tem que ser preenchido.")
        }

        if (!isValidCpf(cpf))
            throw InvalidCustomerDataException("CPF inválido.")

        if (isCpfAlreadyRegistered(cpf))
            throw InvalidCustomerDataException("CPF já cadastrado.")

        if (isEmailAlreadyRegistered(customerDto.email!!))
            throw InvalidCustomerDataException("Email já cadastrado.")

        return customerDto
    }
}