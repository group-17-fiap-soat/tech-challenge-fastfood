package tech.challenge.fastfood.fastfood.adapters.gateways

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.presenters.CustomerMapper
import tech.challenge.fastfood.fastfood.common.interfaces.datasource.CustomerDataSource
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.CustomerGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Customer
import java.util.*

@Component
class CustomerGateway(
    val customerDataSource: CustomerDataSource
) : CustomerGatewayInterface {
    override fun save(entity: Customer): Customer {
        val customerEntity = CustomerMapper.toDAO(entity)
        return CustomerMapper.fromDaoToEntity(customerDataSource.save(customerEntity));
    }

    override fun findByCpf(cpf: String): Customer? {
        return CustomerMapper.fromDaoToEntity(customerDataSource.findByCpf(cpf).orElse(null))
    }

    override fun findById(id: UUID): Customer? {
        return CustomerMapper.fromDaoToEntity(customerDataSource.findById(id).orElse(null))
    }

    override fun findByEmail(email: String): Customer? {
        return CustomerMapper.fromDaoToEntity(customerDataSource.findFirstByEmail(email).orElse(null))
    }

}
