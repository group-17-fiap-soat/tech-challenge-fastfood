package tech.challenge.fastfood.fastfood.common.interfaces.datasource

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.common.dao.PaymentDAO
import java.util.UUID

@Repository
interface PaymentDataSource: JpaRepository<PaymentDAO, UUID> {
    fun findByOrderId(id: UUID): PaymentDAO?
    fun findByExternalId(id:Long): PaymentDAO?
}