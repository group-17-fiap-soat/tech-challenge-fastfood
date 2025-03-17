package tech.challenge.fastfood.fastfood.common.interfaces.gateway;

import tech.challenge.fastfood.fastfood.common.dtos.CustomerDto
import tech.challenge.fastfood.fastfood.common.daos.CustomerDAO
import java.util.*

interface CustomerGatewayInterface {
    fun save(entity: CustomerDto): CustomerDAO
    fun findByCpf(cpf: String): CustomerDAO?
    fun findByEmail(email: String): CustomerDAO?
    fun findById(id: UUID): CustomerDAO?
}
