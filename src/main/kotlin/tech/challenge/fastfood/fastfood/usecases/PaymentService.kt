package tech.challenge.fastfood.fastfood.usecases

import com.mercadopago.MercadoPagoConfig
import com.mercadopago.client.payment.PaymentClient
import com.mercadopago.client.payment.PaymentCreateRequest
import com.mercadopago.client.payment.PaymentPayerRequest
import com.mercadopago.resources.payment.Payment
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class PaymentService{
    init {
        MercadoPagoConfig.setAccessToken("TEST-2888144853903955-031900-fb822b506960da281a9a1d332a0e23a7-1568273131")
    }

    data class PixPagamentoDTO(val id: Long, val qrCode: String, val qrCodeImagem: String, val status: String)

    fun criarPagamentoPix(valor: BigDecimal, emailCliente: String): PixPagamentoDTO {
        val paymentClient = PaymentClient()

        val paymentRequest = PaymentCreateRequest.builder()
            .transactionAmount(valor)
            .paymentMethodId("pix")
            .payer(PaymentPayerRequest.builder().email(emailCliente).build())
            .build()


        val pagamento: Payment = paymentClient.create(paymentRequest)

        val transactionData = pagamento.pointOfInteraction.transactionData
        val qrCode = transactionData.qrCode
        val qrCodeBase64 = transactionData.qrCodeBase64
        val status = pagamento.status

        return PixPagamentoDTO(pagamento.id, qrCode, qrCodeBase64, status)
    }

    fun consultarStatusPagamento(paymentId: Long): String {
        val paymentClient = PaymentClient()
        val pagamento = paymentClient.get(paymentId)
        return pagamento.status
    }
}