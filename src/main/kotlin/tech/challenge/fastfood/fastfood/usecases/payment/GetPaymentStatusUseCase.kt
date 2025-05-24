package tech.challenge.fastfood.fastfood.usecases.payment

import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.PaymentGatewayInterface
import java.util.*

@Service
class GetPaymentStatusUseCase(
    val paymentGatewayInterface: PaymentGatewayInterface
) {
    fun execute(paymentId: UUID): String? {
        return paymentGatewayInterface.findById(paymentId)?.paymentStatus?.status
    }
}