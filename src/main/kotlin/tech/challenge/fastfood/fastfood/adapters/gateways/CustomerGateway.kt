package tech.challenge.fastfood.fastfood.adapters.gateways

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.presenters.CustomerMapper
import tech.challenge.fastfood.fastfood.common.daos.CustomerDAO
import tech.challenge.fastfood.fastfood.common.dtos.CustomerDto
import tech.challenge.fastfood.fastfood.common.interfaces.datasource.CustomerDataSource
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.CustomerGatewayInterface
import java.util.*

@Component
class CustomerGateway(
    val customerDataSource: CustomerDataSource
) : CustomerGatewayInterface {
    override fun save(entity: CustomerDto): CustomerDAO {
        val customerEntity = CustomerMapper.toEntity(entity)
        return customerDataSource.save(customerEntity)
    }

    override fun findByCpf(cpf: String): CustomerDAO? {
        return customerDataSource.findByCpf(cpf).orElse(null)
    }

    override fun findById(id: UUID): CustomerDAO? {
        return customerDataSource.findById(id).orElse(null)
    }

    override fun findByEmail(email: String): CustomerDAO? {
        return customerDataSource.findFirstByEmail(email).orElse(null)
    }

}
