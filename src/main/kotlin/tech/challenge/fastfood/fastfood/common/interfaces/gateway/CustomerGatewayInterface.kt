package tech.challenge.fastfood.fastfood.common.interfaces.gateway;

import tech.challenge.fastfood.fastfood.entities.Customer
import java.util.*

interface CustomerGatewayInterface {
    fun save(entity: Customer): Customer
    fun findByCpf(cpf: String): Customer?
    fun findByEmail(email: String): Customer?
    fun findById(id: UUID): Customer?
}
