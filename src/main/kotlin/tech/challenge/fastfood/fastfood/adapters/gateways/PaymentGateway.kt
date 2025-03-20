package tech.challenge.fastfood.fastfood.adapters.gateways

import tech.challenge.fastfood.fastfood.common.interfaces.datasource.PaymentDataSource
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.PaymentGatewayInterface
import tech.challenge.fastfood.fastfood.entities.PaymentData

class PaymentGateway(
    val paymentDataSource: PaymentDataSource
): PaymentGatewayInterface {
    override fun save(entity: PaymentData) {

    }

}