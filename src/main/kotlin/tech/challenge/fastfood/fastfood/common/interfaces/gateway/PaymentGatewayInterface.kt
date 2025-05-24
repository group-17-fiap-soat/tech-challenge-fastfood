package tech.challenge.fastfood.fastfood.common.interfaces.gateway

import tech.challenge.fastfood.fastfood.entities.PaymentData
import java.util.*

interface PaymentGatewayInterface {
    fun save(entity: PaymentData): PaymentData
    fun findByOrderId(id: UUID): PaymentData?
    fun findByExternalId(id: Long): PaymentData?
    fun findById(id: UUID): PaymentData?
}