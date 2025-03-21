package tech.challenge.fastfood.fastfood.usecases.payment

import com.mercadopago.client.payment.PaymentClient
import com.mercadopago.client.payment.PaymentCreateRequest
import com.mercadopago.client.payment.PaymentPayerRequest
import com.mercadopago.resources.payment.Payment
import com.mercadopago.resources.payment.PaymentTransactionData
import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.enums.PaymentStatusEnum
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.PaymentGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Order
import tech.challenge.fastfood.fastfood.entities.PaymentData
import tech.challenge.fastfood.fastfood.entities.PaymentAssociation


@Service
class CreatePaymentUseCase(
    private val paymentGatewayInterface: PaymentGatewayInterface,
    private val paymentClient: PaymentClient
) {
    companion object {
        private const val DEFAULT_PAYMENT_METHOD: String = "pix"
        private const val WEBHOOK_URL: String = "https://springboot.local.com/api/payments/webhook"
    }

    fun execute(order: Order, paymentMethod: String? = DEFAULT_PAYMENT_METHOD): PaymentAssociation {

        val paymentRequest = PaymentCreateRequest.builder()
            .transactionAmount(order.totalPrice)
            .paymentMethodId(paymentMethod)
            .payer(PaymentPayerRequest.builder().email("cliente@gmail.com").build())
            .build()

        val payment: Payment = paymentClient.create(paymentRequest)
        val transactionData = payment.pointOfInteraction.transactionData

        val paymentData = paymentGatewayInterface.save(
            PaymentData(
                orderId = order.id,
                externalId = payment.id,
                totalAmount = order.totalPrice,
                paymentMethod = paymentMethod!!,
                paymentStatus = PaymentStatusEnum.getByStatus(payment.status)
            )
        )

        return PaymentAssociation(
            paymentData = paymentData
        ).evaluateData(DEFAULT_PAYMENT_METHOD, transactionData)
    }

    private fun PaymentAssociation.evaluateData(paymentMethod: String, transactionData: PaymentTransactionData) =
        when(paymentMethod){
            DEFAULT_PAYMENT_METHOD -> this.copy(data = mapOf(
                "qrCode" to transactionData.qrCode,
                "qrCodeBase64" to transactionData.qrCodeBase64
            ))
            else -> this.copy()
        }
}