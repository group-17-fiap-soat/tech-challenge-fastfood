package tech.challenge.fastfood.fastfood.usecases.payment

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.enums.PaymentStatusEnum
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.PaymentGatewayInterface

@Service
class ProcessPaymentUseCase(
    val getPaymentStatusUseCase: GetPaymentStatusUseCase,
    val paymentGatewayInterface: PaymentGatewayInterface,
){
    fun execute(externalId: Long, action: String){
        if (action == "payment.updated") {
            val status = getPaymentStatusUseCase.execute(externalId)
            val payment = paymentGatewayInterface.findByExternalId(externalId)
            payment?.copy(paymentStatus = PaymentStatusEnum.getByStatus(status))
                ?.let(paymentGatewayInterface::save)
        }
    }
}