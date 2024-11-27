package tech.challenge.fastfood.fastfood.infra.adapters

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.application.dto.CustomerDto
import tech.challenge.fastfood.fastfood.domain.model.CustomerEntity
import tech.challenge.fastfood.fastfood.domain.port.CustomerRepositoryPort
import tech.challenge.fastfood.fastfood.infra.adapters.repositories.CustomerJPAInterface
import tech.challenge.fastfood.fastfood.infra.mapper.CustomerMapper
import java.util.*

@Component
class CustomerAdapter(
    val customerJPAInterface: CustomerJPAInterface
) : CustomerRepositoryPort {
    override fun save(entity: CustomerDto): CustomerEntity {
        val customerEntity = CustomerMapper.toEntity(entity)

        return customerJPAInterface.save(customerEntity)
    }

    override fun findByCpf(cpf: String): CustomerEntity? {
        return customerJPAInterface.findByCpf(cpf).orElse(null)
    }

    override fun findById(id: UUID): CustomerEntity? {
        return customerJPAInterface.findById(id).orElse(null)
    }

    override fun findByEmail(email: String): CustomerEntity? {
        return customerJPAInterface.findFirstByEmail(email).orElse(null)
    }

}
