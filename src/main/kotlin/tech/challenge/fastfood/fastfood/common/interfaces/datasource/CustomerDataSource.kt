package tech.challenge.fastfood.fastfood.common.interfaces.datasource

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.common.dao.CustomerDAO
import java.util.*

@Repository
interface CustomerDataSource : JpaRepository<CustomerDAO, UUID> {
    fun findByCpf(cpf: String): Optional<CustomerDAO>
    fun findFirstByEmail(email: String): Optional<CustomerDAO>
}