package tech.challenge.fastfood.fastfood.common.interfaces.datasource

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import tech.challenge.fastfood.fastfood.common.daos.OrderDAO
import java.util.*

@Repository
interface OrderDataSource: JpaRepository<OrderDAO, UUID> {
}