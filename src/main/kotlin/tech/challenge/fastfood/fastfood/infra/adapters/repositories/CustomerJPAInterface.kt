package tech.challenge.fastfood.fastfood.infra.adapters.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.domain.model.CustomerEntity
import java.util.*

@Repository
interface CustomerJPAInterface : JpaRepository<CustomerEntity, UUID> {
    fun findByCpf(cpf: String): Optional<CustomerEntity>
    fun findFirstByEmail(email: String): Optional<CustomerEntity>
}