package tech.challenge.fastfood.fastfood.domain.port;

import tech.challenge.fastfood.fastfood.application.dto.CustomerDto
import tech.challenge.fastfood.fastfood.domain.model.CustomerEntity
import java.util.*

interface CustomerRepositoryPort {
    fun save(entity: CustomerDto): CustomerEntity
    fun findByCpf(cpf: String): CustomerEntity?
    fun findByEmail(email: String): CustomerEntity?
    fun findById(id: UUID): CustomerEntity?
}
