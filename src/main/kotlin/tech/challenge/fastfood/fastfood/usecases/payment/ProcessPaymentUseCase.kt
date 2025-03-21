package tech.challenge.fastfood.fastfood.usecases.payment

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.enums.OrderStatusEnum
import tech.challenge.fastfood.fastfood.common.enums.PaymentStatusEnum
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.OrderGatewayInterface
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.PaymentGatewayInterface

@Service
class ProcessPaymentUseCase(
    private val paymentGatewayInterface: PaymentGatewayInterface,
    private val orderGatewayInterface: OrderGatewayInterface,
){
    fun execute(externalId: Long, action: String){
        if (action == "payment.updated") {
//            val status = getPaymentStatusUseCase.execute(externalId)
            val status =  PaymentStatusEnum.PAYMENT_APPROVED.status
            val payment = paymentGatewayInterface.findByExternalId(externalId)
            payment?.copy(paymentStatus = PaymentStatusEnum.getByStatus(status))
                ?.let(paymentGatewayInterface::save)
                ?: EntityNotFoundException("Payment with id $externalId not found")

            val order = orderGatewayInterface.findById(payment!!.orderId!!)
            orderGatewayInterface.save(order!!.copy(status = OrderStatusEnum.RECEIVED))
        }
    }
}