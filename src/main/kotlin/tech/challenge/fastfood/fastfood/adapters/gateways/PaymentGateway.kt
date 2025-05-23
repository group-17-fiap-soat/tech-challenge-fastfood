package tech.challenge.fastfood.fastfood.adapters.gateways

import org.springframework.stereotype.Component
import tech.challenge.fastfood.fastfood.adapters.presenters.PaymentMapper
import tech.challenge.fastfood.fastfood.common.interfaces.datasource.PaymentDataSource
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.PaymentGatewayInterface
import tech.challenge.fastfood.fastfood.entities.PaymentData
import java.util.UUID

@Component
class PaymentGateway(
    private val paymentDataSource: PaymentDataSource
): PaymentGatewayInterface {
    override fun save(entity: PaymentData): PaymentData {
        return paymentDataSource.save(PaymentMapper.toDao(entity)).let(PaymentMapper::toEntity)
    }

    override fun findByOrderId(id: UUID): PaymentData? {
        return paymentDataSource.findByOrderId(id)?.let(PaymentMapper::toEntity)
    }

    override fun findByExternalId(id: Long): PaymentData? {
        return paymentDataSource.findByExternalId(id)?.let(PaymentMapper::toEntity)
    }

    override fun findById(id: UUID): PaymentData? {
        return paymentDataSource.findById(id).orElse(null)?.let(PaymentMapper::toEntity)
    }
}