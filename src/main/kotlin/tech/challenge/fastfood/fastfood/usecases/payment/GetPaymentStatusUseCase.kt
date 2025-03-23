package tech.challenge.fastfood.fastfood.usecases.payment

import com.mercadopago.client.payment.PaymentClient
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import tech.challenge.fastfood.fastfood.common.interfaces.datasource.PaymentDataSource
import java.util.UUID

@Service
class GetPaymentStatusUseCase(
    val paymentDataSource: PaymentDataSource
) {
    fun execute(paymentId: UUID): String? {
        return paymentDataSource.findById(paymentId).orElse(null).paymentStatus.status
    }
}