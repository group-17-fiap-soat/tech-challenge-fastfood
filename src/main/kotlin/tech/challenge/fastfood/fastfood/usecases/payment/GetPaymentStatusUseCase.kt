package tech.challenge.fastfood.fastfood.usecases.payment

import com.mercadopago.client.payment.PaymentClient
import org.springframework.stereotype.Service

@Service
class GetPaymentStatusUseCase {

    fun execute(paymentId: Long): String {
        val paymentClient = PaymentClient()
        val payment = paymentClient.get(paymentId)
        return payment.status
    }
}