package tech.challenge.fastfood.fastfood.application.service

import tech.challenge.fastfood.fastfood.application.dto.CustomerDto
import java.util.*

interface CustomerService {
    fun createCustomer(customerDto: CustomerDto): CustomerDto
    fun getCustomerByCpf(cpf: String): CustomerDto?
    fun getCustomerById(id: UUID): CustomerDto?
}