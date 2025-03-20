package tech.challenge.fastfood.fastfood.usecases.payment

import com.mercadopago.MercadoPagoConfig
import com.mercadopago.client.payment.PaymentClient
import com.mercadopago.client.payment.PaymentCreateRequest
import com.mercadopago.client.payment.PaymentPayerRequest
import com.mercadopago.resources.payment.Payment
import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.dto.response.PixPaymentResponseV1
import java.math.BigDecimal


@Service
class CreatePaymentUseCase(
    private val paymentClient: PaymentClient
){
    fun execute(value: BigDecimal, email: String): PixPaymentResponseV1 {

        val paymentRequest = PaymentCreateRequest.builder()
            .transactionAmount(value)
            .paymentMethodId("pix")
            .payer(PaymentPayerRequest.builder().email(email).build())
            .build()

        val payment: Payment = paymentClient.create(paymentRequest)

        val transactionData = payment.pointOfInteraction.transactionData
        val qrCode = transactionData.qrCode
        val qrCodeBase64 = transactionData.qrCodeBase64
        val status = payment.status

        return PixPaymentResponseV1(payment.id, qrCode, qrCodeImage = qrCodeBase64, status)
    }


}