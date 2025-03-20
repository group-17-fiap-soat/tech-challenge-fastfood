package tech.challenge.fastfood.fastfood.common.interfaces.gateway

import tech.challenge.fastfood.fastfood.entities.PaymentData

interface PaymentGatewayInterface {
    fun save(entity: PaymentData): PaymentData
}