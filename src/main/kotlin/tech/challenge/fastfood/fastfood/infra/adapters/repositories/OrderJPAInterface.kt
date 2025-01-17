package tech.challenge.fastfood.fastfood.infra.adapters.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.domain.model.OrderEntity
import java.util.*

@Repository
interface OrderJPAInterface: JpaRepository<OrderEntity, UUID> {
}