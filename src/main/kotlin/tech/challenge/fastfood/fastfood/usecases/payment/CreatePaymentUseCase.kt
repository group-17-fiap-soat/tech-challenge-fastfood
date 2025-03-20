package tech.challenge.fastfood.fastfood.usecases.payment

import com.mercadopago.client.payment.PaymentClient
import com.mercadopago.client.payment.PaymentCreateRequest
import com.mercadopago.client.payment.PaymentPayerRequest
import com.mercadopago.resources.payment.Payment
import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.dto.response.PixPaymentResponseV1
import tech.challenge.fastfood.fastfood.common.enums.PaymentStatusEnum
import tech.challenge.fastfood.fastfood.common.interfaces.gateway.PaymentGatewayInterface
import tech.challenge.fastfood.fastfood.entities.Order
import tech.challenge.fastfood.fastfood.entities.PaymentData
import tech.challenge.fastfood.fastfood.entities.PixPaymentAssociaiton
import java.math.BigDecimal


@Service
class CreatePaymentUseCase(
    private val paymentGatewayInterface: PaymentGatewayInterface,
    private val paymentClient: PaymentClient
){
    fun execute(value: BigDecimal, email: String, order: Order): PixPaymentAssociaiton {

        val paymentRequest = PaymentCreateRequest.builder()
            .transactionAmount(value)
            .paymentMethodId("pix")
            .payer(PaymentPayerRequest.builder().email(email).build())
            .build()

        val payment: Payment = paymentClient.create(paymentRequest)

        val transactionData = payment.pointOfInteraction.transactionData
        val qrCode = transactionData.qrCode
        val qrCodeBase64 = transactionData.qrCodeBase64

        val paymentData = paymentGatewayInterface.save(
            PaymentData(
                orderId = order.id,
                externalId = payment.id,
                totalAmount = order.totalPrice,
                paymentMethod = "pix",
                paymentStatus = PaymentStatusEnum.getByStatus(payment.status)
            )
        )

        return PixPaymentAssociaiton(paymentData = paymentData, qrCode = qrCode, qrCodeImage = qrCodeBase64)
    }

}